package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Exp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpRepository extends CrudRepository<Exp, Integer> {
}
