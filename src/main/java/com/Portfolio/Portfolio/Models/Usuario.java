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

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "canModify", columnDefinition = "boolean default false")
    private Boolean canModify = false;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Skill> skills;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Educacion> educaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    Set<Exp> exps = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    Set<Proyecto> proyectos = new HashSet<>();
}
