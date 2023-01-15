package com.Portfolio.Portfolio.Controllers;

import com.Portfolio.Portfolio.Models.LogIn;
import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Service.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
   
    @Autowired
    UsuarioService userService;
    
    @PostMapping
    public Boolean login (@RequestBody LogIn userLog){
        Usuario user = userService.findUsuario(1);
        System.out.println(user.getPassword().equals(userLog.getPassword()));
        return user.getPassword().equals(userLog.getPassword());
    }
    
}
