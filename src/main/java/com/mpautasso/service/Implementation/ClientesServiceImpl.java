package com.mpautasso.service.Implementation;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.exception.InvalidPetitionException;
import com.mpautasso.mapper.ClienteMapper;
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

    @Override
    public List<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clientesRepository.findAll();
        return clientes.stream().map(clienteMapper::clienteEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public ClienteResponse crearCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.clienteRequestToEntity(clienteRequest);
        if (cliente.getEmpresa() != null) {
            cliente.setEmpresa(empresaService.buscarEmpresaPorId(cliente.getEmpresa().getId()));
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
    public ClienteResponse editarCliente(ClienteRequest clienteRequest) {
        Cliente clienteActualizado = clienteMapper.clienteRequestToEntity(clienteRequest);
        Optional<Cliente> clienteBD = clientesRepository.findById(clienteActualizado.getId());
        if(clienteBD.isEmpty()){
            throw new EntityNotFoundException("No se encontró el cliente a actualizar");
        }
        if(!clienteBD.get().getEmpresa().equals(clienteActualizado.getEmpresa())){
            throw new InvalidPetitionException("No se puede cambiar la empresa asociada al cliente.");
        }
        return null;
    }

    @Override
    public void eliminarCliente(long id) {

    }
}
