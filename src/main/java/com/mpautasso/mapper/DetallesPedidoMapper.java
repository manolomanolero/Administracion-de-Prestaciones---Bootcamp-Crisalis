package com.mpautasso.mapper;

import com.mpautasso.dto.detallesPedidos.DetallesPedidoRequest;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoResponse;
import com.mpautasso.dto.impuestos.ImpuestoResponse;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.model.DetallesPedido;
import com.mpautasso.model.Impuesto;
import com.mpautasso.model.Prestacion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetallesPedidoMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ImpuestosMapper impuestosMapper;
    @Autowired
    private PrestacionMapper prestacionMapper;

    public DetallesPedido requestToEntity(DetallesPedidoRequest detallesPedidoRequest){
        List<Impuesto> impuestos = detallesPedidoRequest.getImpuestos()
                                        .stream().map(impuestosMapper::fromImpuestoRequestToEntity)
                                        .collect(Collectors.toList());

        Prestacion prestacion = prestacionMapper.prestacionUpdateRequestToEntity(detallesPedidoRequest.getPrestacion());

        return new DetallesPedido(detallesPedidoRequest.getId(), detallesPedidoRequest.getCantidad(), prestacion,
                impuestos, detallesPedidoRequest.getGarantia());
    }

    public DetallesPedidoResponse entityToResponse(DetallesPedido detallesPedido){
        List<ImpuestoResponse> impuestosResponse = detallesPedido.getImpuestos()
                .stream().map(impuestosMapper::fromEntityToResponse)
                .collect(Collectors.toList());
        PrestacionResponse prestacion = prestacionMapper.prestacionEntityToDto(detallesPedido.getPrestacion());
        return new DetallesPedidoResponse(
                detallesPedido.getId(), detallesPedido.getCantidad(), prestacion, detallesPedido.getGarantia(),
                impuestosResponse, detallesPedido.getCostoProducto(), detallesPedido.getCostoNeto()
        );
    }
}
