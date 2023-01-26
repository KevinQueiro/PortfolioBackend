package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "https://kq-portfolio.web.app")
public class UsuarioController {

    @Autowired
    UsuarioService userService;

    @GetMapping("/all")
    public List<Usuario> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/save")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario user) {

        Map<String, Object> response = new HashMap<>();

        try {
            userService.saveUser(user);
            response.put("Usuario agregado", user);
        } catch (Exception e) {
            response.put("Mensaje", "Algo salio mal");
            response.put("error", e.getMessage());
            response.put("Datos Requeridos", "*nombre*, *apellido*, titulo, about, foto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
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

        if (user.getPassword() == null) {
            toChange.setPassword(toChange.getPassword());
        } else {
            toChange.setPassword(user.getPassword());
        }
        toChange.setNombre(user.getNombre());
        toChange.setApellido(user.getApellido());
        toChange.setTitulo(user.getTitulo());
        toChange.setAbout(user.getAbout());
        toChange.setFoto(user.getFoto());

        userService.saveUser(toChange);

        return toChange;
    }

    @GetMapping("/one/{id}")
    public Usuario showUser(@PathVariable("id") Integer id) {
        return userService.findUsuario(id);
    }

}
