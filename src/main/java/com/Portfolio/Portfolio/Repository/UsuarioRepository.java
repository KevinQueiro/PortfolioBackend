package com.Portfolio.Portfolio.Repository;

import com.Portfolio.Portfolio.Models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
