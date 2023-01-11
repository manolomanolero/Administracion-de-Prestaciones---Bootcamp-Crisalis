package com.mpautasso.service;

import com.mpautasso.dto.detallesPedidos.DetallesPedidoRequest;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoResponse;

import java.util.List;

public interface DetallesPedidosService {
    List<DetallesPedidoResponse> findAll();
    List<DetallesPedidoResponse> findAllByPedidoId(Long id);
    DetallesPedidoResponse findById(Long id);

    DetallesPedidoResponse create(DetallesPedidoRequest detallePedidoRequest);
    DetallesPedidoResponse update(DetallesPedidoRequest detallesPedidoRequest);
    void delete(Long id);

}
