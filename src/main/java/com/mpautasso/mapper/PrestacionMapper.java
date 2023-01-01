package com.mpautasso.mapper;

import com.mpautasso.dto.prestaciones.PrestacionRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.dto.prestaciones.PrestacionUpdateRequest;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.model.Prestacion;
import com.mpautasso.model.Producto;
import com.mpautasso.model.Servicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrestacionMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PrestacionResponse prestacionEntityToDto(Prestacion prestacion) {
        return new PrestacionResponse(prestacion.getId(), prestacion.getNombre(), prestacion.getCosto(), prestacion.getType());
    }

    public Prestacion prestacionRequestToEntity(PrestacionRequest prestacionRequest) {
        if (prestacionRequest.getTipo().equalsIgnoreCase("servicio")) {
            return new Servicio(prestacionRequest.getNombre(), prestacionRequest.getCosto());
        }
        if (prestacionRequest.getTipo().equalsIgnoreCase("producto")) {
            return new Producto(prestacionRequest.getNombre(), prestacionRequest.getCosto());
        }
        throw new InvalidArgumentException("Tipo invalido de prestacion");
    }

    public Prestacion prestacionUpdateRequestToEntity(PrestacionUpdateRequest prestacionRequest) {
        if (prestacionRequest.getTipo().equalsIgnoreCase("servicio")) {
            return new Servicio(prestacionRequest.getId(), prestacionRequest.getNombre(), prestacionRequest.getCosto());
        }
        if (prestacionRequest.getTipo().equalsIgnoreCase("producto")) {
            return new Producto(prestacionRequest.getId(), prestacionRequest.getNombre(), prestacionRequest.getCosto());
        }
        throw new InvalidArgumentException("Tipo invalido de prestacion");
    }
}
