package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Exp;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Exp.ExpService;
import com.Portfolio.Portfolio.Service.Tecnologia.TecnologiaService;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
@RequestMapping("/exp")
@CrossOrigin(origins = "http://localhost:4200")
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
    public Exp crearOnlyExp(@RequestParam(name = "tecno", defaultValue = "0") List<String> tecno,
            @RequestParam(name = "user", defaultValue = "0") Integer usuarioId,
            @RequestBody Exp exp) {
        //se busca el proyecto y el usuario con sus respectivos ids
        Usuario usuario = userService.findUsuario(usuarioId);

        //se recorre el array de tecnologias
        for (String eachTecno : tecno) {
            if (tecnoService.findTecno(Integer.valueOf(eachTecno)) == null) {
                System.out.println("no existe una tecnologia con id: " + eachTecno);
            } else {
                //se busca cada tecnologia por su i
                Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                //se agregrega la tecnologia a la relacion desde ambos lados exp--->tec y tec--->exp
                exp.getTecnologias().add(toAdd);
                toAdd.getExps().add(exp);
            }
        }
        if (usuario == null) {
            System.out.println("No existe un usuario con id: " + usuarioId);
        } else {
            //si no esta vinculado se lo vincula
            exp.setUsuario(usuario);
        }
        expService.saveExp(exp);
        return exp;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExp(@PathVariable("id") Integer id) {
        Exp exp = expService.findExp(id);
        for (Tecnologia eachTecno : exp.getTecnologias()) {
            eachTecno.getExps().removeIf(t -> t.getId() == id);
        }
        if (expService.deleteExp(id)) {
            return "Experiencia eliminada";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change")
    public Exp changeExp(@RequestParam(name = "tecno", defaultValue = "0") List<String> tecno,
            @RequestParam(name = "exp", defaultValue = "0") Integer expId,
            @RequestParam(name = "user", defaultValue = "0") Integer usuarioId,
            @RequestBody Exp toChangeExp,
            @RequestParam(name = "unbind", defaultValue = "false") boolean unbind) {

        System.out.println(tecno);
        System.out.println(expId);
        System.out.println(usuarioId);
        System.out.println(unbind);

        //se busca el proyecto y el usuario con sus respectivos ids
        Exp exp = expService.findExp(expId);
        Usuario usuario = userService.findUsuario(usuarioId);

        if (exp == null) {
            System.out.println("No existe una experiencia con id: " + expId);
        } else {
            //se cambian los campos sin relaciones de la tabla proyecto
            exp.setDescripcion(toChangeExp.getDescripcion());
            exp.setLugar(toChangeExp.getLugar());
            exp.setFechaIni(toChangeExp.getFechaIni());
            exp.setFechaFin(toChangeExp.getFechaFin());
            exp.setFoto(toChangeExp.getFoto());
            //se recorre el array de tecnologias
            for (String eachTecno : tecno) {
                if (tecnoService.findTecno(Integer.valueOf(eachTecno)) == null) {
                    System.out.println("no existe una tecnologia con id: " + eachTecno);
                } else {
                    if (unbind) {
                        Tecnologia toUnbind = tecnoService.findTecno(Integer.valueOf(eachTecno));
                        exp.getTecnologias().removeIf((t) -> t.getId() == Integer.valueOf(eachTecno));
                        toUnbind.getExps().removeIf((t) -> t.getId() == expId);
                    } else {
                        //se busca cada tecnologia por su i
                        Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
                        //se agregrega la tecnologia a la relacion desde ambos lados exp--->tec y tec--->exp
                        exp.getTecnologias().add(toAdd);
                        toAdd.getExps().add(exp);
                    }
                }
            }
            if (usuario == null) {
                System.out.println("No existe un usuario con id: " + usuarioId);
            } else {
                //se verifica que el proyecto no este ya relacionado al actual usuario
                if (exp.getUsuario() == usuario) {
                    if (unbind) {
                        exp.setUsuario(null);
                        //usuario.getProyectos().removeIf((t) -> t.getId() == proyectId);
                    }
                } else {
                    //si no esta vinculado se lo vincula
                    exp.setUsuario(usuario);
                }
            }
            expService.saveExp(exp);
        }
        return exp;
    }

    @GetMapping("/one/{id}")
    public Exp showExp(@PathVariable("id") Integer id) {
        return expService.findExp(id);
    }

}
