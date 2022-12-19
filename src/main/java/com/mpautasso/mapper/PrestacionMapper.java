package com.mpautasso.mapper;

import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.model.Productos;
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
}
