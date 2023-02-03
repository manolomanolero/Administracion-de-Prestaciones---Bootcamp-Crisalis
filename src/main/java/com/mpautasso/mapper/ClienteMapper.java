package com.mpautasso.mapper;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.cliente.ClienteUpdateRequest;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.model.Cliente;
import com.mpautasso.model.ConsumidorFinal;
import com.mpautasso.model.Empresa;
import com.mpautasso.model.RepresentanteEmpresa;
import com.mpautasso.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  EmpresaMapper empresaMapper;
    @Autowired
    private EmpresaRepository empresaRepository;

    public ClienteResponse clienteEntityToResponse(Cliente cliente){
        if(cliente.getEmpresa() == null){
            return new ClienteResponse(cliente.getId(),
                    cliente.getDni(), cliente.getNombre(), cliente.getApellido());
        } else if(cliente.getEmpresa() != null){
            return new ClienteResponse(cliente.getId(), cliente.getDni(), cliente.getNombre(), cliente.getApellido(),
                                        empresaMapper.empresaEntityToResponse(cliente.getEmpresa()));
        }
        throw new InvalidArgumentException("Tipo invalido de prestacion");
    }

    public Cliente clienteRequestToEntity(ClienteRequest clienteRequest){
        if(clienteRequest.getEmpresa() != null
                && clienteRequest.getEmpresa().getId() != null && clienteRequest.getEmpresa().getId() > 0){
            Optional<Empresa> empresa = empresaRepository.findById(clienteRequest.getEmpresa().getId());
            if(empresa.isEmpty()){
                throw new InvalidArgumentException("No se encontró la empresa en sistema.");
            }
            return new RepresentanteEmpresa(
                    clienteRequest.getDni(),
                    clienteRequest.getNombre(),
                    clienteRequest.getApellido(),
                    empresa.get());
        } else {
            return new ConsumidorFinal(clienteRequest.getDni(), clienteRequest.getNombre(),
                    clienteRequest.getApellido());
        }
    }

    public Cliente clienteUpdateRequestToEntity(ClienteUpdateRequest clienteRequest){
        if(clienteRequest.getEmpresa() != null
                && clienteRequest.getEmpresa().getId() != null && clienteRequest.getEmpresa().getId() > 0){
            Optional<Empresa> empresa = empresaRepository.findById(clienteRequest.getEmpresa().getId());
            if(empresa.isEmpty()){
                throw new InvalidArgumentException("No se encontró la empresa en sistema.");
            }
            return new RepresentanteEmpresa(
                    clienteRequest.getId(),
                    clienteRequest.getDni(),
                    clienteRequest.getNombre(),
                    clienteRequest.getApellido(),
                    empresa.get());
        } else {
            return new ConsumidorFinal(clienteRequest.getId(), clienteRequest.getDni(), clienteRequest.getNombre(),
                    clienteRequest.getApellido());
        }
    }

    public Cliente clienteResponseToEntity(ClienteResponse clienteResponse){
        if(clienteResponse.getEmpresa() != null){
            Optional<Empresa> empresa = empresaRepository.findById(clienteResponse.getEmpresa().getId());
            return new RepresentanteEmpresa(
                    clienteResponse.getId(),
                    clienteResponse.getDni(),
                    clienteResponse.getNombre(),
                    clienteResponse.getApellido(),
                    empresa.get());
        } else {
            return new ConsumidorFinal(clienteResponse.getId(), clienteResponse.getDni(), clienteResponse.getNombre(),
                    clienteResponse.getApellido());
        }
    }


}
