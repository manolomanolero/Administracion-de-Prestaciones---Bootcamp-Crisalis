package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "inicio_actividades")
    @Temporal(TemporalType.DATE)
    private Date inicioActividades;

    public Empresa(Long id) {
        this.id = id;
    }

    public Empresa(Long cuit, String razonSocial, Date inicioActividades) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.inicioActividades = inicioActividades;
    }

/* TODO: implementar fecha de inicio
    fechaInicio
     */

}
