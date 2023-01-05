package com.Portfolio.Portfolio.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Percent")
    private Integer percen;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;
}
