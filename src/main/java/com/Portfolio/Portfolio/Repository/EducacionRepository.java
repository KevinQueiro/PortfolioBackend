package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Educacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends CrudRepository<Educacion, Integer> {
}
