package com.mpautasso.mapper;

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

    public Pedido fromRequestToEntity(PedidoRequest pedidoRequest){
        return new Pedido(
                clientesService.buscarEntidad(
                        pedidoRequest.getClienteId()
                ),
                pedidoRequest.getDetallesPedidos().stream()
                        .map(detallesPedidoMapper::requestToEntity)
                        .collect(Collectors.toList())
        );
    }

    public Pedido fromUpdateRequestToEntity(PedidoUpdateRequest pedidoRequest){
        return new Pedido(
                pedidoRequest.getId(),
                clienteMapper.clienteRequestToEntity(pedidoRequest.getCliente()),
                pedidoRequest.getFechaPedido(),
                pedidoRequest.getDetallesPedidos().stream()
                        .map(detallesPedidoMapper::requestToEntity)
                        .collect(Collectors.toList())
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
}
