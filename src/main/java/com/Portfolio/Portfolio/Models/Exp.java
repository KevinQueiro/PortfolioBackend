package com.Portfolio.Portfolio.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Column(name = "FechaIni")
    private Date fechaIni;

    @Column(name = "FechaFin")
    private Date fechaFin;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;

    //@JsonIgnore
    //@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@JoinTable(name = "exp_tecnologia", 
    //        joinColumns = @JoinColumn(name = "exp_id"),
    //        inverseJoinColumns = @JoinColumn(name = "tecnologia_id"))
    //List<Tecnologia> tecnologiaExp = new ArrayList<Tecnologia>();
}
