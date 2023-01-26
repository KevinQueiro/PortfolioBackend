package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Skill;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Skill.SkillService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "https://kq-portfolio.web.app")
public class SkillController {

    @Autowired
    SkillService skillService;

    @Autowired
    UsuarioService userService;

    @GetMapping("/all")
    public List<Skill> getAllSkill() {
        return skillService.getAllSkill();
    }

    @PostMapping("/save/{idUser}")
    public Skill crearSkill(@RequestBody Skill skill,
            @PathVariable("idUser") Integer idUser) {
        try {
            if(skill.getPercen()!=""&&skill.getNombre()!=""){
            Usuario user = userService.findUsuario(idUser);
            skill.setUsuario(user);
            return skillService.saveSkill(skill);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @PostMapping("/save")
    public Skill crearOnlySkill(@RequestBody Skill skill) {
        try {
            return skillService.saveSkill(skill);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSkill(@PathVariable("id") Integer id) {
        if (skillService.deleteSkill(id)) {
            return "Skill eliminada";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change/{id}")
    public Skill changeOnlySkill(@PathVariable("id") Integer id, 
            @RequestBody Skill skill) {
        Skill toChange = skillService.findSkill(id);

        toChange.setNombre(skill.getNombre());
        toChange.setPercen(skill.getPercen()+"%");
        
        skillService.saveSkill(toChange);
        return toChange;
    }

    @PutMapping("/change/{id}/{user_id}")
    public Skill changeSkill(@PathVariable("id") Integer id,
            @PathVariable("user_id") Integer idUser,
            @RequestBody Skill skill) {
        Skill toChange = skillService.findSkill(id);
        Usuario user = userService.findUsuario(idUser);

        toChange.setNombre(skill.getNombre());
        toChange.setPercen(skill.getPercen());
        toChange.setUsuario(user);

        skillService.saveSkill(toChange);

        return toChange;
    }

    @GetMapping("/one/{id}")
    public Skill showSkill(@PathVariable("id") Integer id) {
        return skillService.findSkill(id);
    }
}
