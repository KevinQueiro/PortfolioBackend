package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Exp;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Exp.ExpService;
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
@RequestMapping("/exp")
public class ExpController {

    @Autowired
    ExpService expService;

    @Autowired
    UsuarioService userService;

    @Autowired
    TecnologiaService tecnoService;

    @GetMapping("/all")
    public List<Exp> getAllExp() {
        return expService.getAllExp();
    }

    @PostMapping("/save")
    public Exp crearOnlyExp(@RequestBody Exp ex) {
        try {
            return expService.saveExp(ex);
        } catch (Exception e) {
            System.out.println("_____________________" + e.getMessage());
            return null;
        }
    }

    /* @PostMapping("/save/{idUser}")
    public Exp crearExp(@RequestBody Exp ex,
    @PathVariable("idUser") Integer idUser,
    @RequestParam("idTecno") Integer idTecno) {
    try {
    Usuario user = userService.findUsuario(idUser);
    ex.setUsuario(user);
    if (idTecno != 0) {
    Tecnologia tecno = tecnoService.findTecno(idTecno);
    ex.getTecnologiaExp().add(tecno);
    tecno.getExp().add(ex);
    }
    return expService.saveExp(ex);
    } catch (Exception e) {
    System.out.println("_____________________" + e.getMessage());
    return null;
    }
    }*/

    @DeleteMapping("/delete/{id}")
    public String deleteExp(@PathVariable("id") Integer id) {
        if (expService.deleteExp(id)) {
            return "Tecnologia eliminada";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change/{id}")
    public Exp changeOnlyExp(@PathVariable("id") Integer id,
            @RequestBody Exp ex) {
        Exp toChange = expService.findExp(id);

        toChange.setDescripcion(ex.getDescripcion());
        toChange.setLugar(ex.getLugar());
        toChange.setFechaIni(ex.getFechaIni());
        toChange.setFechaFin(ex.getFechaFin());

        expService.saveExp(toChange);

        return toChange;
    }

    @PutMapping("/change/{id}/{user_id}")
    public Exp changeExp(@PathVariable("id") Integer id,
            @PathVariable("user_id") Integer idUser,
            @RequestBody Exp ex) {
        Exp toChange = expService.findExp(id);
        Usuario user = userService.findUsuario(idUser);

        toChange.setDescripcion(ex.getDescripcion());
        toChange.setLugar(ex.getLugar());
        toChange.setFechaIni(ex.getFechaIni());
        toChange.setFechaFin(ex.getFechaFin());
        toChange.setUsuario(user);

        expService.saveExp(toChange);

        return toChange;
    }

}
