package com.mpautasso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalles_impuestos_pedido")
public class DetallesImpuestosPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "impuesto_id", referencedColumnName = "id")
    private Impuesto impuesto;

    private Double monto;

    public DetallesImpuestosPedido(Impuesto impuesto, Double monto){
        this.impuesto = impuesto;
        this.monto = monto;
    }
}
