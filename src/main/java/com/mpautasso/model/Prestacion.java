package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_prestacion",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Prestacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double costo;

    public Prestacion(String nombre, Double costo){
        this.nombre = nombre;
        this.costo = costo;
    }
}
