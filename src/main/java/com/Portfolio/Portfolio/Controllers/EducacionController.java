package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Educacion;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Educacion.EducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {

    @Autowired
    EducacionService eduService;

    @Autowired
    UsuarioService userService;

    @GetMapping("/all")
    public List<Educacion> getAllEdu() {
        return eduService.getAllEdu();
    }

    @PostMapping("/save")
    public Educacion crearOnlyProyecto(@RequestParam(name = "user", defaultValue = "0") Integer usuarioId,
            @RequestBody Educacion edu) {

        Usuario usuario = userService.findUsuario(usuarioId);

        if (usuario == null) {
            System.out.println("No existe un usuario con id: " + usuarioId);
        } else {
            edu.setUsuario(usuario);
        }
        eduService.saveEdu(edu);
        return edu;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Integer id) {
        if (eduService.deleteEdu(id)) {
            return "Educacion eliminada";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change/{id}")
    public Educacion changeOnlyEdu(@PathVariable("id") Integer id,
            @RequestBody Educacion edu) {
        System.out.println("change education");
        Educacion toChange = eduService.findEdu(id);

        toChange.setFoto(edu.getFoto());
        toChange.setLugar(edu.getLugar());
        toChange.setTitulo(edu.getTitulo());
        toChange.setFechaFin(edu.getFechaFin());
        eduService.saveEdu(toChange);
        return toChange;
    }

    @PutMapping("/change/{id}/{user_id}")
    public Educacion changeEdu(@PathVariable("id") Integer id, @PathVariable("user_id") Integer idUser, @RequestBody Educacion edu) {
        Educacion toChange = eduService.findEdu(id);
        Usuario user = userService.findUsuario(idUser);

        toChange.setLugar(edu.getLugar());
        toChange.setTitulo(edu.getTitulo());
        toChange.setFechaFin(edu.getFechaFin());
        toChange.setUsuario(user);

        eduService.saveEdu(toChange);

        return toChange;
    }

    @GetMapping("/one/{id}")
    public Educacion showEdu(@PathVariable("id") Integer id) {
        return eduService.findEdu(id);
    }

}
