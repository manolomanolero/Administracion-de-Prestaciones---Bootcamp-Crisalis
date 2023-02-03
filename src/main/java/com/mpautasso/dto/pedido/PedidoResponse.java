package com.mpautasso.dto.pedido;

import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Long id;
    private ClienteResponse cliente;
    private Date fecha;
    private Double costoBruto;
    private Double costoFinal;
    private List<DetallesPedidoResponse> detallesPedidos;
}
