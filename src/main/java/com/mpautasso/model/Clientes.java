package com.mpautasso.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="empresa_id",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dni;

    private String nombre;
    private String apellido;

    /*
    List<Servicios> serviciosContratados;
     */
}
