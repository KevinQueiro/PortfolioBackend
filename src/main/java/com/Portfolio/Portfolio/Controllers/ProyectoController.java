package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Proyecto.ProyectoService;
import com.Portfolio.Portfolio.Service.Tecnologia.TecnologiaService;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Proyecto crearOnlyProyecto(@RequestBody Proyecto pro) {
        try {
            return proService.saveProject(pro);
        } catch (Exception e) {
            System.out.println("______________" + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Integer id) {
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
            @RequestBody Proyecto toChangePro) {

        //asegurarse que lleguen los tres datos pedidos (un id de proyecto, un id de usuario y un array tecnologias)
        if (Integer.parseInt(tecno.get(0)) != 0 && proyectId != 0 && usuarioId != 0) {

            //se busca el proyecto y el usuario con sus respectivos ids
            Proyecto proyecto = proService.findProject(proyectId);
            Usuario usuario = userService.findUsuario(usuarioId);

            //se cambian los campos sin relaciones de la tabla proyecto
            proyecto.setDescripcion(toChangePro.getDescripcion());
            proyecto.setLink(toChangePro.getLink());

            //se recorre el array de tecnologias
            for (String eachTecno : tecno) {

                //se busca cada tecnologia por su i
                Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                System.out.println(toAdd.getNombre());

                //se agregrega la tecnologia a la relacion desde ambos lados pro--->tec y tec--->pro
                proyecto.getTecnologias().add(toAdd);
                toAdd.getProyectos().add(proyecto);

            }

            //se verifica que el proyecto no este ya relacionado al actual usuario
            if (proyecto.getUsuario() == usuario) {

                System.out.println("El usuario " + usuario.getUserName() + " ya esta conectado al proyecto: " + proyecto.getDescripcion());

            } else {

                //si no esta vinculado se lo vincula
                System.out.println(usuario.getUserName());
                proyecto.setUsuario(usuario);

            }

            proService.saveProject(proyecto);
            System.out.println("LINE 80: " + proyecto.getTecnologias());
            return proyecto;

        } else {
            System.out.println("Falta algo");
            return null;
        }

    }

}
