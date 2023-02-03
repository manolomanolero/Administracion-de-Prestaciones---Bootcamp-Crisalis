package com.mpautasso.service.Implementation;

import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.model.Cliente;
import com.mpautasso.model.ClientesServiciosContratados;
import com.mpautasso.repository.ClientesServiciosContratadosRepository;
import com.mpautasso.service.ClientesService;
import com.mpautasso.service.ServiciosContratadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiciosContratadosServiceImpl implements ServiciosContratadosService {
    private final ClientesServiciosContratadosRepository serviciosContratadosRepository;
    private final ClientesService clientesService;

    @Override
    public List<ClientesServiciosContratados> listarServicios() {
        return serviciosContratadosRepository.findAll();
    }

    @Override
    public ClientesServiciosContratados buscarServicioPorId(Long id) {
        return serviciosContratadosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el servicio en el servidor"));
    }

    @Override
    public List<ClientesServiciosContratados> buscarServiciosPorIdCliente(Long idCliente) {
        Cliente cliente = clientesService.buscarEntidad(idCliente);
        return serviciosContratadosRepository.findAllByCliente(cliente);
    }

    @Override
    public ClientesServiciosContratados desactivarServicio(Long id) {
        ClientesServiciosContratados servicioContratados =
                serviciosContratadosRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el servicio en el servidor"));

        servicioContratados.setEstaActivo(false);
        servicioContratados.setUltimaActualizacion(new Date());

        return serviciosContratadosRepository.save(servicioContratados);
    }

    @Override
    public ClientesServiciosContratados activarServicio(Long id) {
        ClientesServiciosContratados servicioContratados =
                serviciosContratadosRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el servicio en el servidor"));

        servicioContratados.setEstaActivo(true);
        servicioContratados.setUltimaActualizacion(new Date());

        return serviciosContratadosRepository.save(servicioContratados);
    }

}
