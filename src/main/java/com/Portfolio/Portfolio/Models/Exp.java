package com.Portfolio.Portfolio.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Exps")
public class Exp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Lugar")
    private String lugar;

    @Column(name = "Foto")
    private String foto;
    
    @Column(name = "FechaIni")
    private Date fechaIni;

    @Column(name = "FechaFin")
    private Date fechaFin;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;

    @ManyToMany(mappedBy = "exps", fetch = FetchType.EAGER)
    private Set<Tecnologia> tecnologias = new HashSet<>();

}
