package com.mpautasso.mapper;

import com.mpautasso.dto.empresa.EmpresaRequest;
import com.mpautasso.dto.empresa.EmpresaResponse;
import com.mpautasso.model.Empresa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    @Autowired
    private ModelMapper modelMapper;
    public EmpresaResponse empresaEntityToResponse(Empresa empresa){
        return modelMapper.map(empresa, EmpresaResponse.class);
    }

    public Empresa empresaRequestToEntity(EmpresaRequest empresaRequest){
        return modelMapper.map(empresaRequest, Empresa.class);
    }
}
