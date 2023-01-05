package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Service.Tecnologia.TecnologiaService;
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
@RequestMapping("/tecnologias")
public class TecnologiaController {

    @Autowired
    TecnologiaService tecnoService;

    /* @GetMapping("/")
    public String hola(){
    return "hola aca estoy";
    }*/
    
    @GetMapping("/all")
    public List<Tecnologia> getAllTecno() {
        return tecnoService.getAllTecno();
    }

    @PostMapping("/save")
    public Tecnologia crearTecnologia(@RequestBody Tecnologia tecno) {
        return tecnoService.saveTecno(tecno);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTecno(@PathVariable("id") Integer id) {
        if (tecnoService.deleteTecno(id)) {
            return "Tecnologia eliminada";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change/{id}")
    public Tecnologia changeTecnology(@PathVariable("id") Integer id, @RequestBody Tecnologia tecno) {
        Tecnologia toChange = tecnoService.findTecno(id);

        toChange.setNombre(tecno.getNombre());
        toChange.setTipo(tecno.getTipo());

        tecnoService.saveTecno(toChange);

        return toChange;
    }

}
