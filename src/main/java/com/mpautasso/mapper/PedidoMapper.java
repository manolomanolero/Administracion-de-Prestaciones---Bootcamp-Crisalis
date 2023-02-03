package com.mpautasso.mapper;

import com.mpautasso.dto.pedido.PedidoCompletoResponse;
import com.mpautasso.dto.pedido.PedidoRequest;
import com.mpautasso.dto.pedido.PedidoResponse;
import com.mpautasso.dto.pedido.PedidoUpdateRequest;
import com.mpautasso.model.Pedido;
import com.mpautasso.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoMapper {
    @Autowired
    private  ClienteMapper clienteMapper;
    @Autowired
    private DetallesPedidoMapper detallesPedidoMapper;
    @Autowired
    private ClientesService clientesService;

    public Pedido fromRequestToEntity(PedidoRequest pedidoRequest, boolean tieneDescuento){
        return new Pedido(
                clientesService.buscarEntidad(
                        pedidoRequest.getCliente().getId()
                ),
                pedidoRequest.getDetallesPedidos().stream()
                        .map(detallesPedidoMapper::requestToEntity)
                        .collect(Collectors.toSet()),
                tieneDescuento
        );
    }

    public Pedido fromUpdateRequestToEntity(PedidoUpdateRequest pedidoRequest, boolean tieneDescuento){
        return new Pedido(
                pedidoRequest.getId(),
                clienteMapper.clienteRequestToEntity(pedidoRequest.getCliente()),
                pedidoRequest.getFechaPedido(),
                pedidoRequest.getDetallesPedidos().stream()
                        .map(detallesPedidoMapper::requestToEntity)
                        .collect(Collectors.toSet()),
                tieneDescuento
        );
    }

    public PedidoResponse fromEntityToResponse(Pedido pedido){
        return new PedidoResponse(
                pedido.getId(),
                clienteMapper.clienteEntityToResponse(pedido.getCliente()),
                pedido.getFechaPedido(),
                pedido.getCostoBruto(),
                pedido.getCostoFinal(),
                pedido.getDetallesPedidoList().stream()
                        .map(detallesPedidoMapper::entityToResponse)
                        .collect(Collectors.toList())
        );
    }

    public PedidoCompletoResponse fromEntityToResponseCompleta(Pedido pedido){
        return new PedidoCompletoResponse(
                pedido.getId(),
                clienteMapper.clienteEntityToResponse(pedido.getCliente()),
                pedido.getFechaPedido(),
                pedido.getCostoBruto(),
                pedido.getCostoConImpuestos(),
                pedido.isTieneDescuento(),
                pedido.getTotalDescuento(),
                pedido.getCostoFinal(),
                pedido.getDetallesPedidoList().stream()
                        .map(detallesPedidoMapper::entityToResponse)
                        .collect(Collectors.toList()),
                pedido.getDetallesImpuestosPedidos()
        );
    }
}
