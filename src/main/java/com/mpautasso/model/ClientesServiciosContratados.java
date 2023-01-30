package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTES_SERVICIOS_CONTRATADOS")
public class ClientesServiciosContratados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "esta_activo")
    private boolean estaActivo;

    @Temporal(TemporalType.DATE)
    @Column(name = "ultima_actualizacion")
    private Date ultimaActualizacion;

    @ManyToOne(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinColumn( name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinColumn( name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;
}
