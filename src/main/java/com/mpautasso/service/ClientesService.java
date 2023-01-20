package com.mpautasso.service;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.cliente.ClienteUpdateRequest;
import com.mpautasso.model.Cliente;

import java.util.List;

public interface ClientesService {
    List<ClienteResponse> listarClientes();
    ClienteResponse buscarCliente(Long id);
    Cliente buscarEntidad(Long id);

    ClienteResponse crearCliente(ClienteRequest clienteRequest);

    ClienteResponse editarCliente(ClienteUpdateRequest clienteRequest);

    void eliminarCliente(long id);

}
