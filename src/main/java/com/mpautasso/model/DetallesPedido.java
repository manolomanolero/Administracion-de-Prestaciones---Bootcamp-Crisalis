package com.mpautasso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalles_pedidos")
public class DetallesPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "prestacion_id", referencedColumnName = "id")
    private Prestacion prestacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedidos_impuestos",
            joinColumns = @JoinColumn(name = "detalle_pedido_id"),
            inverseJoinColumns =  @JoinColumn(name = "impuesto_id")
    )
    List<Impuesto> impuestos;

    @Column(name = "costo_producto", nullable = false)
    private Double costoProducto;

    @Column(name = "costo_neto", nullable = false)
    private Double costoNeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    //2% del valor del producto por año. Solo para productos
    @Column(nullable = false)
    private Integer garantia;

    public DetallesPedido(Long id, int cantidad, Prestacion prestacion, List<Impuesto> impuestos, Integer garantia) {
        this.id = id;
        this.cantidad = cantidad;
        this.prestacion = prestacion;
        this.impuestos = impuestos;
        this.garantia = garantia;
        this.costoProducto = prestacion.getCosto();
        this.costoNeto = calcularCostoTotal();
    }

    private double calcularCostoTotal(){
        Double result = prestacion.getCosto() * this.cantidad;
        Double porcentajeImpuestos = impuestos.stream().mapToDouble(Impuesto::getPorcentaje).sum();
        result += result * porcentajeImpuestos / 100;
        result += result * garantia * 0.02;
        return result * cantidad;
    }
}
