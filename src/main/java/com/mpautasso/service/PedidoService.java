package com.mpautasso.service;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.pedido.PedidoRequest;
import com.mpautasso.dto.pedido.PedidoResponse;
import com.mpautasso.dto.pedido.PedidoUpdateRequest;

import java.util.List;

public interface PedidoService {
    List<PedidoResponse> listarPedidos();
    List<PedidoResponse> listarPedidosDeCliente(ClienteRequest cliente);
    PedidoResponse buscarPedido(Long id);
    PedidoResponse crearPedido(PedidoRequest pedidoRequest);
    PedidoResponse editarPedido(PedidoUpdateRequest pedidoUpdateRequest);
    void eliminarPedido(Long id);
}
