package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService userService;

    @GetMapping("/all")
    public List<Usuario> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/save")
    public Usuario crearUsuario(@RequestBody Usuario user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        if (userService.deleteUser(id)) {
            return "Usuario eliminado";
        } else {
            return "hubo un problema";
        }
    }

    @PutMapping("/change/{id}")
    public Usuario changeUser(@PathVariable("id") Integer id, @RequestBody Usuario user) {
        Usuario toChange = userService.findUsuario(id);

        toChange.setUserName(user.getUserName());
        toChange.setPassword(user.getPassword());

        userService.saveUser(toChange);

        return toChange;
    }

}
