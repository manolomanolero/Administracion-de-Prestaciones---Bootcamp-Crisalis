package com.mpautasso.service.Implementation;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.cliente.ClienteUpdateRequest;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.exception.InvalidPetitionException;
import com.mpautasso.mapper.ClienteMapper;
import com.mpautasso.mapper.EmpresaMapper;
import com.mpautasso.model.Cliente;
import com.mpautasso.repository.ClientesRepository;
import com.mpautasso.service.ClientesService;
import com.mpautasso.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientesServiceImpl implements ClientesService {
    private final ClientesRepository clientesRepository;
    private final EmpresaService empresaService;
    private final ClienteMapper clienteMapper;
    private final EmpresaMapper empresaMapper;

    @Override
    public List<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clientesRepository.findAll();
        return clientes.stream().map(clienteMapper::clienteEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public ClienteResponse buscarCliente(Long id) {
        Optional<Cliente> clienteOpt = clientesRepository.findById(id);
        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente");
        }
        return clienteMapper.clienteEntityToResponse(clienteOpt.get());
    }

    @Override
    public Cliente buscarEntidad(Long id) {
        Optional<Cliente> clienteOpt = clientesRepository.findById(id);
        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente");
        }
        return clienteOpt.get();
    }

    @Override
    public ClienteResponse crearCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.clienteRequestToEntity(clienteRequest);
        if (cliente.getEmpresa() != null) {
            cliente.setEmpresa(empresaMapper.empresaResponseToEntity(
                    empresaService.buscarEmpresaPorId(cliente.getEmpresa().getId())));
        }
        Optional<Cliente> clienteConMismoDniYEmpresa = clientesRepository.findByDniAndEmpresaId(cliente.getDni(),cliente.getEmpresa());
        if(clienteConMismoDniYEmpresa.isPresent() && !clienteConMismoDniYEmpresa.get().getId().equals(cliente.getId())){
            throw new InvalidPetitionException("Ya existe un cliente con mismo dni y empresa que representa");
        }
        Optional<Cliente> clienteBD = clientesRepository.findByDniAndEmpresaId(cliente.getDni(), cliente.getEmpresa());
        if (clienteBD.isPresent()) {
            if (cliente.getEmpresa() != null) {
                throw new InvalidArgumentException("Ya existe este consumidor final.");
            } else {
                throw new InvalidArgumentException("Ya existe" + cliente.getNombre() + " " + cliente.getApellido() +
                        " como representante de " + cliente.getEmpresa().getRazonSocial());
            }
        }
        cliente = clientesRepository.save(cliente);
        return clienteMapper.clienteEntityToResponse(clientesRepository.findById(cliente.getId()).get());
    }

    @Override
    public ClienteResponse editarCliente(ClienteUpdateRequest clienteRequest) {
        Cliente clienteActualizado = clienteMapper.clienteUpdateRequestToEntity(clienteRequest);
        Optional<Cliente> clienteConMismoDniYEmpresa = clientesRepository.findByDniAndEmpresaId(clienteActualizado.getDni(),clienteActualizado.getEmpresa());
        if(clienteConMismoDniYEmpresa.isPresent() && !clienteConMismoDniYEmpresa.get().getId().equals(clienteActualizado.getId())){
            throw new InvalidPetitionException("Ya existe un cliente con mismo dni y empresa que representa");
        }
        Optional<Cliente> clienteOptBD = clientesRepository.findById(clienteActualizado.getId());
        if(clienteOptBD.isEmpty()){
            throw new EntityNotFoundException("No se encontró el cliente a actualizar");
        }
        Cliente clienteBD = clienteOptBD.get();
        clienteBD.setEmpresa(clienteActualizado.getEmpresa());
        clienteBD.setDni(clienteActualizado.getDni());
        clienteBD.setNombre(clienteActualizado.getNombre());
        clienteBD.setApellido(clienteActualizado.getApellido());
        /*if(clienteBD instanceof RepresentanteEmpresa && clienteBD.getEmpresa() == null){
            return clienteMapper.clienteEntityToResponse(clientesRepository.save(clienteBD));
        }*/
        return clienteMapper.clienteEntityToResponse(clientesRepository.save(clienteBD));
    }

    @Override
    public void eliminarCliente(long id) {
        Optional<Cliente> clienteOpt = clientesRepository.findById(id);
        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente a eliminar");
        } else {
            clientesRepository.delete(clienteOpt.get());
        }
    }

}
