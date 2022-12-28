package com.mpautasso.mapper;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.model.Cliente;
import com.mpautasso.model.ConsumidorFinal;
import com.mpautasso.model.Empresa;
import com.mpautasso.model.RepresentanteEmpresa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  EmpresaMapper empresaMapper;

    public ClienteResponse clienteEntityToResponse(Cliente cliente){
        if(cliente.getType().equalsIgnoreCase("consumidor final")){
            return new ClienteResponse(cliente.getId(),
                    cliente.getDni(), cliente.getNombre(), cliente.getApellido());
            //return modelMapper.map(cliente, ClienteResponse.class);
        }
        if(cliente.getType().equalsIgnoreCase("representante empresa")){
            return new ClienteResponse(cliente.getId(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(),
                                        empresaMapper.empresaEntityToResponse(cliente.getEmpresa()));
            //return modelMapper.map(cliente, ClienteResponse.class);
        }
        throw new InvalidArgumentException("Tipo invalido de prestacion");
    }

    public Cliente clienteRequestToEntity(ClienteRequest clienteRequest){
        if(clienteRequest.getEmpresaId() > 0){
            Empresa empresa = new Empresa(clienteRequest.getEmpresaId());
            return new RepresentanteEmpresa(
                    clienteRequest.getDni(),
                    clienteRequest.getNombre(),
                    clienteRequest.getApellido(),
                    empresa);
        } else {
            return new ConsumidorFinal(clienteRequest.getDni(), clienteRequest.getNombre(),
                    clienteRequest.getApellido());
        }
    }


}
