package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long cuit;

    @Column(name = "razon_social", unique = true)
    private String razonSocial;

    public Empresa(Long id) {
        this.id = id;
    }

    public Empresa(Long cuit, String razonSocial) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
    }

/* TODO: implementar fecha de inicio
    fechaInicio
     */

}
