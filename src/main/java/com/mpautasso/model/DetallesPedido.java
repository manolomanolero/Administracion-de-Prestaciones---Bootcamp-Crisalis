package com.mpautasso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalles_pedidos")
public class DetallesPedido {
    @Id
    @SequenceGenerator(
            name = "detalle_pedido_sequence",
            sequenceName = "detalle_pedido_sequence",
            allocationSize = 5,
            initialValue = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "detalle_pedido_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "prestacion_id")
    private Prestacion prestacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedidos_impuestos",
            joinColumns = @JoinColumn(name = "detalle_pedido_id"),
            inverseJoinColumns =  @JoinColumn(name = "impuesto_id")
    )
    Set<Impuesto> impuestos;

    @Column(name = "costo_producto", nullable = false)
    private Double costoProducto;

    @Column(name = "costo_neto", nullable = false)
    private Double costoNeto;


    //2% del valor del producto por año. Solo para productos
    @Column(nullable = false)
    private Integer garantia;

    @Column(nullable = false)
    private boolean soporteEspecial;

    public DetallesPedido(Long id, int cantidad, Prestacion prestacion, Set<Impuesto> impuestos, Integer garantia, boolean soporteEspecial) {
        this.id = id;
        this.cantidad = cantidad;
        this.prestacion = prestacion;
        this.impuestos = impuestos;
        this.garantia = garantia;
        this.soporteEspecial = soporteEspecial;
        this.costoProducto = prestacion.getCosto();
        this.costoNeto = calcularCostoTotal();
    }

    private double calcularCostoTotal(){
        Double result = costoTotalSinImpuestos();
        Double porcentajeImpuestos = impuestos.stream().mapToDouble(Impuesto::getPorcentaje).sum();
        result += result * porcentajeImpuestos / 100;
        return result;
    }

    public List<DetallesImpuestosPedido> detallesImpuestos(){
        List<DetallesImpuestosPedido> detallesImpuestosPedido = new ArrayList<>();
        for(Impuesto impuesto : impuestos){
            Double monto = costoTotalSinImpuestos();
            monto = monto * impuesto.getPorcentaje() / 100;
            detallesImpuestosPedido.add(new DetallesImpuestosPedido(impuesto, monto));
        }
        return detallesImpuestosPedido;
    }

    private Double costoTotalSinImpuestos() {
        Double result = this.prestacion.getCosto() * this.cantidad;
        if(prestacion.getType().equalsIgnoreCase("servicio") && this.soporteEspecial){
            result += prestacion.getCargoSoporte();
        } else if(prestacion.getType().equalsIgnoreCase("producto")) {
            result += result * garantia * 0.02;
        }
        return result;
    }

    public Double calcularDescuento(){
        if(this.prestacion.getType().equalsIgnoreCase("servicio")){
            return 0d;
        } else {
            Double result = costoNeto * 0.1;
            return (result < 2500d) ? result : 2500d;
        }
    }
}
