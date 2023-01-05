package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Tecnologia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends CrudRepository<Tecnologia, Integer> {
}
