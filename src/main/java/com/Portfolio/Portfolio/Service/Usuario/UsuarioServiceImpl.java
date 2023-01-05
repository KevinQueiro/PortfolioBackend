package com.Portfolio.Portfolio.Service.Usuario;

import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository userRepository;

    @Override
    public List<Usuario> getAllUser() {
        return (List<Usuario>) userRepository.findAll();
    }

    @Override
    public Usuario saveUser(Usuario user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Usuario findUsuario(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
