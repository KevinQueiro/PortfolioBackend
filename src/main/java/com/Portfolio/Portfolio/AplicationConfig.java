package com.Portfolio.Portfolio;

import com.Portfolio.Portfolio.Models.Usuario;
import com.Portfolio.Portfolio.Repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicationConfig {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository userRepository) {
        return args -> {
            System.out.println("Aplication runing");
            Optional<Usuario> user = userRepository.findById(1);
            if (user.isEmpty()) {
                userRepository.save(new Usuario("Kevin Damian", "Queiro", "Desarrollador Full Stack", "#KevinQueiro1991"));
            }
        };
    }
}
 