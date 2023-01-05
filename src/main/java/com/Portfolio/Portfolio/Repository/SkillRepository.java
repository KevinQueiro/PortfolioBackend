package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
