package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto, Integer> {
}
