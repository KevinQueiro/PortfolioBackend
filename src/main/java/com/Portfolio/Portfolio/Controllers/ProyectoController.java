package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Proyecto.ProyectoService;
import com.Portfolio.Portfolio.Service.Tecnologia.TecnologiaService;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
            @RequestParam(name = "user", defaultValue = "0") Integer usuarioId) {

        System.out.println(tecno);
        System.out.println(proyectId);

        Proyecto proyecto = proService.findProject(proyectId);
        Usuario usuario = userService.findUsuario(usuarioId);
        if (Integer.parseInt(tecno.get(0)) != 0 && proyectId != 0 && usuarioId != 0) {

            for (String eachTecno : tecno) {

                Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                System.out.println(toAdd.getNombre());
                proyecto.getTecnologias().add(toAdd);
                toAdd.getProyectos().add(proyecto);

            }
            System.out.println(usuario.getUserName());
            proyecto.setUsuario(usuario);

            proService.saveProject(proyecto);
            System.out.println("LINE 80: " + proyecto.getTecnologias());
            return proyecto;

        } else {
            System.out.println("Falta algo");
            return null;
        }

    }

}
