package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Proyecto.ProyectoService;
import com.Portfolio.Portfolio.Service.Tecnologia.TecnologiaService;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://kq-portfolio.web.app")
public class ProyectoController {

    @Autowired
    ProyectoService proService;

    @Autowired
    UsuarioService userService;

    @Autowired
    TecnologiaService tecnoService;

    @GetMapping("/all")
    public List<Proyecto> getAllProjec() {
        return proService.getAllProjec();
    }

    @PostMapping("/save")
    public Proyecto crearOnlyProyecto(@RequestParam(name = "tecno", defaultValue = "0") List<String> tecno,
            @RequestParam(name="user", defaultValue = "0") Integer usuarioId,
            @RequestBody Proyecto pro) {
        
        Usuario usuario = userService.findUsuario(usuarioId);
        
        for (String eachTecno : tecno){
            if ( tecnoService.findTecno(Integer.valueOf(eachTecno)) == null){
                System.out.println("no existe una tecnologia con id: " + eachTecno);
            } else {
                Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                pro.getTecnologias().add(toAdd);
                toAdd.getProyectos().add(pro);
            }
        }
        if (usuario == null) {
            System.out.println("No existe un usuario con id: " + usuarioId);
        } else {
            pro.setUsuario(usuario);
        }
        proService.saveProject(pro);
        return pro;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Integer id) {
        Proyecto pro = proService.findProject(id);
        for (Tecnologia eachTecno : pro.getTecnologias()){
            eachTecno.getProyectos().removeIf(t -> t.getId() == id);
        }
        if (proService.deleteProject(id)) {
            return "Proyecto eliminado";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change")
    public Proyecto updateProyecto(@RequestParam(name = "tecno", defaultValue = "0") List<String> tecno,
            @RequestParam(name = "pro", defaultValue = "0") Integer proyectId,
            @RequestParam(name = "user", defaultValue = "0") Integer usuarioId,
            @RequestBody Proyecto toChangePro,
            @RequestParam(name = "unbind", defaultValue = "false") boolean unbind) {

        //se busca el proyecto y el usuario con sus respectivos ids
        Proyecto proyecto = proService.findProject(proyectId);
        Usuario usuario = userService.findUsuario(usuarioId);

        if (proyecto == null) {
            System.out.println("No existe un proyecto con id: " + proyectId);
        } else {
            //se cambian los campos sin relaciones de la tabla proyecto
            proyecto.setDescripcion(toChangePro.getDescripcion());
            proyecto.setLink(toChangePro.getLink());
            proyecto.setFoto(toChangePro.getFoto());
            proyecto.setNombre(toChangePro.getNombre());
            //se recorre el array de tecnologias
            for (String eachTecno : tecno) {
                if (tecnoService.findTecno(Integer.valueOf(eachTecno)) == null) {
                    System.out.println("no existe una tecnologia con id: " + eachTecno);
                } else {
                    if (unbind) {
                        Tecnologia toUnbind = tecnoService.findTecno(Integer.valueOf(eachTecno));
                        proyecto.getTecnologias().removeIf((t) -> t.getId() == Integer.valueOf(eachTecno));
                        toUnbind.getProyectos().removeIf((t) -> t.getId() == proyectId);
                    } else {
                        //se busca cada tecnologia por su i
                        Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                        //se agregrega la tecnologia a la relacion desde ambos lados pro--->tec y tec--->pro
                        proyecto.getTecnologias().add(toAdd);
                        toAdd.getProyectos().add(proyecto);
                    }
                }
            }
            if (usuario == null) {
                System.out.println("No existe un usuario con id: " + usuarioId);
            } else {
                //se verifica que el proyecto no este ya relacionado al actual usuario
                if (proyecto.getUsuario() == usuario) {
                    if (unbind) {
                        proyecto.setUsuario(null);
                    }
                } else {
                    //si no esta vinculado se lo vincula
                    proyecto.setUsuario(usuario);
                }
            }
            proService.saveProject(proyecto);
        }
        return proyecto;
    }
    
     @GetMapping("/one/{id}")
    public Proyecto showPro(@PathVariable("id") Integer id) {
        return proService.findPro(id);
    }

    
}
