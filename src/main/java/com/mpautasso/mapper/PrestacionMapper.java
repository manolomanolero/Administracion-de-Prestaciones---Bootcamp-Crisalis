package com.mpautasso.mapper;

import com.mpautasso.dto.PrestacionResponse;
import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.dto.ServicioRequest;
import com.mpautasso.model.Prestacion;
import com.mpautasso.model.Productos;
import com.mpautasso.model.Servicios;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrestacionMapper {
    @Autowired
    private ModelMapper modelMapper;
    //TODO implementaciones con model mapper

    public Productos productoRequestToEntity(ProductoRequest productoRequest){
        return new Productos(productoRequest.getNombre(), productoRequest.getCosto());
    }

    public Servicios servicioRequestToEntity(ServicioRequest servicioRequest){
        return new Servicios(servicioRequest.getNombre(), servicioRequest.getCosto());
    }

    public PrestacionResponse prestacionEntityToDto(Prestacion prestacion){
        return new PrestacionResponse(prestacion.getNombre(), prestacion.getCosto(), prestacion.getType());
    }
}
