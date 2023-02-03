package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prestaciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_prestacion",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Prestacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Double costo;

    public Prestacion(String nombre, Double costo) {
        this.nombre = nombre.toLowerCase();
        this.costo = costo;
    }

    public abstract String getType();

    public Double getCargoSoporte(){
        return 0D;
    }
}
