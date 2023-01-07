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

    @DeleteMapping("/delete/{id}")
    public String deleteExp(@PathVariable("id") Integer id) {
        if (expService.deleteExp(id)) {
            return "Tecnologia eliminada";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change")
    public Exp changeExp(@RequestParam(name = "tecno", defaultValue = "0") List<String> tecno,
            @RequestParam(name = "exp", defaultValue = "0") Integer expId,
            @RequestParam(name = "user", defaultValue = "0") Integer usuarioId,
            @RequestBody Exp toChangeExp) {

        if (Integer.parseInt(tecno.get(0)) != 0 && expId != 0 && usuarioId != 0) {

            Exp exp = expService.findExp(expId);
            Usuario usuario = userService.findUsuario(usuarioId);

            exp.setDescripcion(toChangeExp.getDescripcion());
            exp.setLugar(toChangeExp.getLugar());
            exp.setFechaFin(toChangeExp.getFechaFin());
            exp.setFechaIni(toChangeExp.getFechaIni());

            for (String eachTecno : tecno) {

                Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                exp.getTecnologias().add(toAdd);
                toAdd.getExps().add(exp);

            }

            if (exp.getUsuario() == usuario) {

                System.out.println("El usuario " + usuario.getUserName() + " ya esta conectado a la experiencia: " + exp.getDescripcion());

            } else {
                
                exp.setUsuario(usuario);
                
            }
            
            expService.saveExp(exp);
            return exp;

        } else {
            
            System.out.println("Faltan datos");
            return null;
            
        }

    }

}
