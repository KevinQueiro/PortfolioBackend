package com.Portfolio.Portfolio.Service.Skill;

import com.Portfolio.Portfolio.Models.Skill;
import com.Portfolio.Portfolio.Repository.SkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkill() {
        return (List<Skill>) skillRepository.findAll();
    }

    @Override
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public boolean deleteSkill(Integer id) {
        try {
            skillRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Skill findSkill(Integer id) {
        return skillRepository.findById(id).orElse(null);
    }

}
