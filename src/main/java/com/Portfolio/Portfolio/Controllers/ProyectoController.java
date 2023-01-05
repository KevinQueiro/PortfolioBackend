package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Proyecto.ProyectoService;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    ProyectoService proService;

    @Autowired
    UsuarioService userService;

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

    @PostMapping("/save/{idUser}")
    public Proyecto crearProyecto(@RequestBody Proyecto pro,
            @PathVariable("idUser") Integer idUser) {
        try {
            Usuario user = userService.findUsuario(idUser);
            pro.setUsuario(user);

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

}
