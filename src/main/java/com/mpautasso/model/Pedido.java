package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade= CascadeType.MERGE)
    @JoinColumn( name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    private Double costoBruto;

    private Double costoFinal;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DetallesPedido> detallesPedidoList;

    public Pedido(Cliente cliente, List<DetallesPedido> detallesPedidoList) {
        this.detallesPedidoList = detallesPedidoList;
        this.cliente = cliente;
        this.fechaPedido = new Date();
        setearCostos(detallesPedidoList);
    }

    public Pedido(Long id, Cliente cliente, Date fechaPedido, List<DetallesPedido> detallesPedidoList) {
        this.id = id;
        this.detallesPedidoList = detallesPedidoList;
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        setearCostos(detallesPedidoList);
    }

    private void setearCostos(List<DetallesPedido> detallesPedidoList){
        Double costoBrutoTemp = 0D, costoFinalTemp = 0D;
        for (DetallesPedido detalles: detallesPedidoList) {
            costoBrutoTemp += detalles.getCostoProducto();
            costoFinalTemp += detalles.getCostoNeto();
        }
        this.costoBruto = costoBrutoTemp;
        this.costoFinal = costoFinalTemp;
    }
}
