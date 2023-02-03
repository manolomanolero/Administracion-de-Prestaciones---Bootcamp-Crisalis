package com.mpautasso.dto.pedido;

import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoResponse;
import com.mpautasso.model.DetallesImpuestosPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCompletoResponse {
    private Long id;
    private ClienteResponse cliente;
    private Date fecha;
    private Double costoBruto;
    private Double costoConImpuestos;
    private boolean tieneDescuento;
    private Double totalDescuento;
    private Double costoFinal;
    private List<DetallesPedidoResponse> detallesPedidos;
    private Set<DetallesImpuestosPedido> detallesImpuestosPedidos;
}
