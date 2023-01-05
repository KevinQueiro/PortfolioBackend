package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Proyecto_Tecnologia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Proyecto_TecnologiaRepository extends CrudRepository<Proyecto_Tecnologia, Integer>{
    
}
