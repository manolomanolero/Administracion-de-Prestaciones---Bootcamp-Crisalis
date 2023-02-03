package com.mpautasso.service;

import com.mpautasso.model.ClientesServiciosContratados;

import java.util.List;

public interface ServiciosContratadosService {
    List<ClientesServiciosContratados> listarServicios();
    ClientesServiciosContratados buscarServicioPorId(Long id);
    List<ClientesServiciosContratados> buscarServiciosPorIdCliente(Long idCliente);
    ClientesServiciosContratados desactivarServicio(Long id);
    ClientesServiciosContratados activarServicio(Long id);
}