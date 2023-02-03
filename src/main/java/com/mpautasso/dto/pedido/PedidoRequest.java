package com.mpautasso.dto.pedido;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteUpdateRequest;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private ClienteUpdateRequest cliente;
    private List<DetallesPedidoRequest> detallesPedidos;
}
