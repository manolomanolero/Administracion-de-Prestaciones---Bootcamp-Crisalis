package com.mpautasso.service.Implementation;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.pedido.PedidoCompletoResponse;
import com.mpautasso.dto.pedido.PedidoRequest;
import com.mpautasso.dto.pedido.PedidoResponse;
import com.mpautasso.dto.pedido.PedidoUpdateRequest;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.ClienteMapper;
import com.mpautasso.mapper.PedidoMapper;
import com.mpautasso.model.ClientesServiciosContratados;
import com.mpautasso.model.Pedido;
import com.mpautasso.model.Servicio;
import com.mpautasso.repository.ClientesServiciosContratadosRepository;
import com.mpautasso.repository.PedidoRepository;
import com.mpautasso.service.ClientesService;
import com.mpautasso.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ClienteMapper clienteMapper;
    private final ClientesService clientesService;
    private final ClientesServiciosContratadosRepository serviciosContratadosRepository;

    @Override
    public List<PedidoResponse> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponse> listarPedidosDeCliente(ClienteRequest cliente) {
        return null;
    }

    @Override
    public PedidoResponse buscarPedido(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el pedido");
        }
        return pedidoMapper.fromEntityToResponse(
                pedidoOpt.get()
        );
    }

    @Override
    public PedidoCompletoResponse buscarPedidoCompleto(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el pedido");
        }
        return pedidoMapper.fromEntityToResponseCompleta(
                pedidoOpt.get()
        );
    }

    @Override
    public PedidoResponse crearPedido(PedidoRequest pedidoRequest) {

        boolean tieneDescuento =
                serviciosContratadosRepository.existsByClienteAndAndEstaActivo(
                        clienteMapper.clienteUpdateRequestToEntity(pedidoRequest.getCliente()),
                        true);


        Pedido pedido = pedidoMapper.fromRequestToEntity(pedidoRequest, tieneDescuento);
        if (pedido.getDetallesPedidoList().size() < 1) {
            throw new InvalidArgumentException("No se puede crear un pedido sin detalles");
        }

        actualizarServiciosContratados(pedido);

        Pedido pedido1 = pedidoRepository.save(pedido);

        PedidoResponse response = pedidoMapper.fromEntityToResponse(pedido1);

        return response;
    }

    @Override
    public PedidoResponse editarPedido(PedidoUpdateRequest pedidoUpdateRequest) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoUpdateRequest.getId());
        if (pedidoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el pedido a editar");
        }
        boolean tieneDescuento =
                serviciosContratadosRepository.existsByClienteAndAndEstaActivo(
                        pedidoOpt.get().getCliente(),
                        true);
        Pedido pedidoBD = pedidoOpt.get();
        Pedido pedidoReq = pedidoMapper.fromUpdateRequestToEntity(pedidoUpdateRequest, tieneDescuento);

        if (pedidoReq.getDetallesPedidoList().size() < 1) {
            throw new InvalidArgumentException("No se puede crear un pedido sin detalles");
        }
        if (pedidoReq.getFechaPedido() != null && pedidoReq.getFechaPedido().after(new Date())) {
            throw new InvalidArgumentException("La fecha no puede ser mayor a la actual");
        }


        pedidoBD.setFechaPedido(
                pedidoReq.getFechaPedido() != null ?
                        pedidoReq.getFechaPedido() :
                        new Date()
        );

        pedidoBD.setCliente(pedidoReq.getCliente());
        pedidoBD.setDetallesPedidoList(pedidoReq.getDetallesPedidoList());
        pedidoBD.setCostoBruto(pedidoReq.getCostoBruto());
        pedidoBD.setTieneDescuento(tieneDescuento);
        pedidoBD.setTotalDescuento(pedidoReq.getTotalDescuento());
        pedidoBD.setCostoConImpuestos(pedidoReq.getCostoConImpuestos());
        pedidoBD.setCostoFinal(pedidoReq.getCostoFinal());
        pedidoBD.setDetallesImpuestosPedidos(pedidoReq.getDetallesImpuestosPedidos());

        PedidoResponse pedidoResponse = pedidoMapper.fromEntityToResponse(
                pedidoRepository.saveAndFlush(pedidoBD)
        );

        actualizarServiciosContratados(pedidoBD);

        return pedidoResponse;
    }

    @Override
    public void eliminarPedido(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el pedido a eliminar");
        }
        pedidoRepository.delete(pedidoOpt.get());
    }

    private void actualizarServiciosContratados(Pedido pedido) {

        List<ClientesServiciosContratados> serviciosContratados =
                serviciosContratadosRepository.findAllByCliente(pedido.getCliente());

        // Guardamos o actualizamos los servicios contratados por el cliente
        pedido.getDetallesPedidoList().stream()
                .filter(elem -> elem.getPrestacion().getType().equalsIgnoreCase("servicio"))
                .forEach(detallesPedido -> {
                    Optional<ClientesServiciosContratados> servicioContOpt =
                            serviciosContratados.stream()
                                    .filter(elem -> elem.getServicio().getId() == detallesPedido.getPrestacion().getId())
                                    .findFirst();
                    if (servicioContOpt.isEmpty()) {
                        ClientesServiciosContratados servicioContratado =
                                new ClientesServiciosContratados(null, true, pedido.getFechaPedido(),
                                        pedido.getCliente(), (Servicio) detallesPedido.getPrestacion());
                        serviciosContratadosRepository.save(servicioContratado);
                    } else {
                        ClientesServiciosContratados servicioContratado = servicioContOpt.get();
                        servicioContratado.setEstaActivo(true);
                        servicioContratado.setUltimaActualizacion(pedido.getFechaPedido());
                        serviciosContratadosRepository.save(servicioContratado);
                    }
                });

    }
}
