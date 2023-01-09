package com.Portfolio.Portfolio.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "about")
    private String about;

    @Column(name = "foto")
    private String foto;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Skill> skills;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Educacion> educaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    Set<Exp> exps = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    Set<Proyecto> proyectos = new HashSet<>();

    public Usuario(String nombre, String apellido, String titulo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.password = password;
    }

    public Usuario(){   
    }
}
