package com.mpautasso.mapper;

import com.mpautasso.dto.impuestos.ImpuestoRequest;
import com.mpautasso.dto.impuestos.ImpuestoResponse;
import com.mpautasso.model.Impuesto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImpuestosMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Impuesto fromImpuestoRequestToEntity(ImpuestoRequest impuestoRequest) {
        return modelMapper.map(impuestoRequest, Impuesto.class);
    }

    public ImpuestoResponse fromEntityToResponse(Impuesto impuesto) {
        return modelMapper.map(impuesto, ImpuestoResponse.class);
    }
}
