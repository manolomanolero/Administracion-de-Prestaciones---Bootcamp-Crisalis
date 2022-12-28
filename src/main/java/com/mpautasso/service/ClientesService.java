package com.mpautasso.service;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;

import java.util.List;

public interface ClientesService {
    List<ClienteResponse> listarClientes();

    ClienteResponse crearCliente(ClienteRequest clienteRequest);

    ClienteResponse editarCliente(ClienteRequest clienteRequest);

    void eliminarCliente(long id);

}
