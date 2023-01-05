package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Models.Proyecto_Tecnologia;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Service.Proyecto.ProyectoService;
import com.Portfolio.Portfolio.Service.Proyecto_Tecnologia.Proyecto_TecnologiaService;
import com.Portfolio.Portfolio.Service.Tecnologia.TecnologiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Proyecto_TecnologiaController {

    @Autowired
    ProyectoService proService;

    @Autowired
    TecnologiaService tecService;

    @Autowired
    Proyecto_TecnologiaService proTecService;

    @GetMapping("/watch")
    public List<Proyecto_Tecnologia> show(){
        return proTecService.getAll();
    }
    
    @PostMapping("/pro-tec")
    public Proyecto_Tecnologia vincular(@RequestParam(name = "pro", defaultValue = "0") Integer pro,
            @RequestParam(name = "tec", defaultValue = "0") List<Integer> tec) {

        if (pro != 0) {
            if (tec.get(0) != 0) {
                System.out.println("tengo todo");

                Proyecto proyecto = proService.findProject(pro);
                
                for (Integer eachTec : tec) {
                    Proyecto_Tecnologia proTec = new Proyecto_Tecnologia();
                    Tecnologia tecnologia = tecService.findTecno(eachTec);
                    proTec.setProyecto(proyecto);
                    proTec.setTecnologia(tecnologia);
                    
                   proTecService.saveProTec(proTec);
                }
                
            } else {
                System.out.println("me faltan tecnologias");
            }
        } else {
            System.out.println("me falta proyecto");
        }

        return null;
    }

}
