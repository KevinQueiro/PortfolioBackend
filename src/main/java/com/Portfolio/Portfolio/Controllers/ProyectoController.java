package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Proyecto;
import com.Portfolio.Portfolio.Models.Proyecto_Tecnologia;
import com.Portfolio.Portfolio.Models.Tecnologia;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Proyecto.ProyectoService;
import com.Portfolio.Portfolio.Service.Proyecto_Tecnologia.Proyecto_TecnologiaService;
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

    /* @Autowired
    TecnologiaService tecnoService;
    
    @Autowired
    Proyecto_TecnologiaService proTecService;*/
    @GetMapping("/all")
    public List<Proyecto> getAllProjec() {
        List<Proyecto> all = proService.getAllProjec();
        for (Proyecto each : all) {
            for (Proyecto_Tecnologia eachP : each.getProyecto_tecnologia()) {
                System.out.println(eachP.getTecnologia());
                
            }
        }
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

    /*    @PutMapping("/change/{id}")
    public Proyecto changeProjec(@PathVariable("id") Integer id,
    @RequestParam(name = "user", defaultValue = "0") Integer user,
    @RequestParam(name = "tecno", defaultValue = "0") List<String> tecno,
    @RequestBody Proyecto pro) {
    
    Proyecto toChange = proService.findProject(id);
    System.out.println(toChange.getId());
    if (!"0".equals(tecno.get(0))) {
    for (String eachTecno : tecno) {
    Tecnologia toAdd = tecnoService.findTecno(Integer.valueOf(eachTecno));
    List<Proyecto_Tecnologia> toPush = toChange.getProyecto_tecnologia();
    
    if (toAdd != null) {
    
    
    Proyecto_Tecnologia nuevo = new Proyecto_Tecnologia();
    nuevo.setProyecto(pro);
    nuevo.setTecnologia(toAdd);
    toPush.add(nuevo);
    System.out.println(toPush.get(0));
    toChange.setDescripcion(pro.getDescripcion());
    proService.saveProject(toChange);
    //
    //System.out.println(nuevo.getProyecto().getDescripcion());
    //System.out.println(nuevo.getTecnologia().getNombre());
    //proTecService.saveProTec(nuevo);
    }
    }
    }
    
    System.out.println("user: " + user);
    
    return toChange;
    }*/
}
