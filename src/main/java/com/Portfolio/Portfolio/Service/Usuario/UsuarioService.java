package com.Portfolio.Portfolio.Service.Usuario;

import com.Portfolio.Portfolio.Models.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> getAllUser();

    Usuario saveUser(Usuario user);

    boolean deleteUser(Integer id);

    Usuario findUsuario(Integer id);

}
