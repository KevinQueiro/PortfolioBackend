package com.Portfolio.Portfolio.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Tecnologias")
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;
    
    @Column(name = "Tipo")
    private String tipo;
    
    @OneToMany(mappedBy = "tecnologia",cascade = CascadeType.ALL)
    List<Proyecto_Tecnologia> proyecto_tecnologias;
}
