package com.Portfolio.Portfolio.Service.Skill;

import com.Portfolio.Portfolio.Models.Skill;
import java.util.List;

public interface SkillService {

    List<Skill> getAllSkill();

    Skill saveSkill(Skill skill);

    boolean deleteSkill(Integer id);

    Skill findSkill(Integer id);
}
