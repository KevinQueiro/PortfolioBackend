package com.Portfolio.Portfolio.Controllers;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.Portfolio.Portfolio.Models.Educacion;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Educacion.EducacionService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
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
    public Educacion crearOnlyEdu(@RequestBody Educacion edu) {
        try {
            return eduService.saveEdu(edu);
        } catch (Exception e) {
            System.out.println("_________________" + e.getMessage());
            return null;
        }
    }

    @PostMapping("/save/{idUser}")
    public Educacion crearEdu(@RequestBody Educacion edu, @PathVariable("idUser") Integer idUser) {
        try {
            Usuario user = userService.findUsuario(idUser);
            edu.setUsuario(user);
            return eduService.saveEdu(edu);
        } catch (Exception e) {
            System.out.println("_________________" + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEdu(@PathVariable("id") Integer id) {
        if (eduService.deleteEdu(id)) {
            return "Educacion Eliminida";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change/{id}")
    public Educacion changeOnlyEdu(@PathVariable("id") Integer id, @RequestBody Educacion edu) {
        Educacion toChange = eduService.findEdu(id);

        toChange.setLugar(edu.getLugar());
        toChange.setTitulo(edu.getTitulo());
        toChange.setFechaFin(edu.getFechaFin());
        eduService.saveEdu(toChange);
        return toChange;
    }

     @PutMapping("/change/{id}/{user_id}")
    public Educacion changeEdu(@PathVariable("id") Integer id,@PathVariable("user_id") Integer idUser, @RequestBody Educacion edu) {
        Educacion toChange = eduService.findEdu(id);
        Usuario user = userService.findUsuario(idUser);
        
        toChange.setLugar(edu.getLugar());
        toChange.setTitulo(edu.getTitulo());
        toChange.setFechaFin(edu.getFechaFin());
        toChange.setUsuario(user);
        
        eduService.saveEdu(toChange);
        
        return toChange;
    }
    
}
