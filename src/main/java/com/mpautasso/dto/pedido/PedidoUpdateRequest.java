package com.mpautasso.dto.pedido;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoUpdateRequest {
    private Long id;
    private ClienteRequest cliente;
    private Date fechaPedido;
    private List<DetallesPedidoRequest> detallesPedidos;
}
