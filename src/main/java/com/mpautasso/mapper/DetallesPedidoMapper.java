package com.mpautasso.mapper;

import com.mpautasso.dto.detallesPedidos.DetallesPedidoRequest;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoResponse;
import com.mpautasso.dto.impuestos.ImpuestoResponse;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.model.DetallesPedido;
import com.mpautasso.model.Impuesto;
import com.mpautasso.model.Prestacion;
import com.mpautasso.repository.PrestacionesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DetallesPedidoMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ImpuestosMapper impuestosMapper;
    @Autowired
    private PrestacionMapper prestacionMapper;
    @Autowired
    private PrestacionesRepository prestacionesRepository;

    public DetallesPedido requestToEntity(DetallesPedidoRequest detallesPedidoRequest){
        Set<Impuesto> impuestos = detallesPedidoRequest.getImpuestos()
                                        .stream().map(impuestosMapper::fromImpuestoRequestToEntity)
                                        .collect(Collectors.toSet());

        Prestacion prestacion = prestacionesRepository
                .findById(detallesPedidoRequest.getPrestacion().getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encotro la prestacion en el servidor"));

        return new DetallesPedido(detallesPedidoRequest.getId(), detallesPedidoRequest.getCantidad(), prestacion,
                impuestos, detallesPedidoRequest.getGarantia(), detallesPedidoRequest.isSoporteEspecial());
    }

    public DetallesPedidoResponse entityToResponse(DetallesPedido detallesPedido){
        Set<ImpuestoResponse> impuestosResponse = detallesPedido.getImpuestos()
                .stream().map(impuestosMapper::fromEntityToResponse)
                .collect(Collectors.toSet());
        PrestacionResponse prestacion = prestacionMapper.prestacionEntityToDto(detallesPedido.getPrestacion());
        return new DetallesPedidoResponse(
                detallesPedido.getId(), detallesPedido.getCantidad(), prestacion, detallesPedido.getGarantia(),
                detallesPedido.isSoporteEspecial(), impuestosResponse,
                detallesPedido.getCostoProducto(), detallesPedido.getCostoNeto()
        );
    }
}
