package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {
    @Id
    @SequenceGenerator(
            name = "pedido_sequence",
            sequenceName = "pedido_sequence",
            allocationSize = 5,
            initialValue = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "detalle_pedido_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    @JoinColumn( name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    private Double costoBruto;

    private Double costoConImpuestos;

    private Double costoFinal;

    private boolean tieneDescuento;
    private Double totalDescuento;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Set<DetallesPedido> detallesPedidoList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Set<DetallesImpuestosPedido> detallesImpuestosPedidos;

    public Pedido(Cliente cliente, Set<DetallesPedido> detallesPedidoList, boolean tieneDescuento) {
        this.detallesPedidoList = detallesPedidoList;
        this.cliente = cliente;
        this.fechaPedido = new Date();
        this.tieneDescuento = tieneDescuento;
        setearCostos(detallesPedidoList);
    }

    public Pedido(Long id, Cliente cliente, Date fechaPedido, Set<DetallesPedido> detallesPedidoList, boolean tieneDescuento) {
        this.id = id;
        this.detallesPedidoList = detallesPedidoList;
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.tieneDescuento = tieneDescuento;
        setearCostos(detallesPedidoList);
    }

    private void setearCostos(Set<DetallesPedido> detallesPedidoList){
        Double costoBrutoTemp = 0D, costoFinalTemp = 0D, descuentosAcumulados = 0D;
        for (DetallesPedido detalles: detallesPedidoList) {
            costoBrutoTemp += detalles.getCostoProducto() * detalles.getCantidad();
            if(this.tieneDescuento) descuentosAcumulados += detalles.calcularDescuento();
            costoFinalTemp += detalles.getCostoNeto();
        }
        calcularImpuestos();
        this.costoBruto = costoBrutoTemp;
        this.totalDescuento = descuentosAcumulados;
        this.costoFinal = costoFinalTemp;
    }

    private void calcularImpuestos(){
        Set<DetallesImpuestosPedido> detallesImpPedido = new HashSet<>();
        HashMap<Long, DetallesImpuestosPedido> hashMap = new HashMap<>();

        if (this.detallesImpuestosPedidos != null) {
            detallesImpPedido = this.detallesImpuestosPedidos;
            for (DetallesImpuestosPedido impuestoDelDetalle : detallesImpPedido){
                hashMap.put(impuestoDelDetalle.getImpuesto().getId() , impuestoDelDetalle);
            }
        }


        for(DetallesPedido detallesPedido : detallesPedidoList){
            List<DetallesImpuestosPedido> impuestosPedidoTemp = detallesPedido.detallesImpuestos();
            for (DetallesImpuestosPedido impuestoDelDetalle : impuestosPedidoTemp){
                DetallesImpuestosPedido impuestoAcumulado = hashMap.get(impuestoDelDetalle.getImpuesto().getId());
                if(impuestoAcumulado != null){
                    impuestoAcumulado.setMonto(impuestoAcumulado.getMonto() + impuestoDelDetalle.getMonto());
                    hashMap.put(impuestoAcumulado.getImpuesto().getId() , impuestoAcumulado);
                } else {
                    hashMap.put(impuestoDelDetalle.getImpuesto().getId() , impuestoDelDetalle);
                }
            }
        }

        this.detallesImpuestosPedidos = hashMap.values().stream().collect(Collectors.toSet());
    }
}
