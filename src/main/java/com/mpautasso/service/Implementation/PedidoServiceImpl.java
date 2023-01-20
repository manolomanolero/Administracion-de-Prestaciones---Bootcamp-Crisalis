package com.mpautasso.service.Implementation;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.pedido.PedidoRequest;
import com.mpautasso.dto.pedido.PedidoResponse;
import com.mpautasso.dto.pedido.PedidoUpdateRequest;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.PedidoMapper;
import com.mpautasso.model.Pedido;
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
    private final ClientesService clientesService;

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
        if(pedidoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el pedido");
        }
        return pedidoMapper.fromEntityToResponse(
                pedidoOpt.get()
        );
    }

    @Override
    public PedidoResponse crearPedido(PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoMapper.fromRequestToEntity(pedidoRequest);
        if(pedido.getDetallesPedidoList().size() < 1){
            throw new InvalidArgumentException("No se puede crear un pedido sin detalles");
        }

        return pedidoMapper.fromEntityToResponse(
                pedidoRepository.save(pedido)
        );
    }

    @Override
    public PedidoResponse editarPedido(PedidoUpdateRequest pedidoUpdateRequest) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoUpdateRequest.getId());
        if(pedidoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el pedido a editar");
        }
        Pedido pedidoBD = pedidoOpt.get();
        Pedido pedidoReq = pedidoMapper.fromUpdateRequestToEntity(pedidoUpdateRequest);

        if(pedidoReq.getDetallesPedidoList().size() < 1){
            throw new InvalidArgumentException("No se puede crear un pedido sin detalles");
        }
        if (pedidoReq.getFechaPedido().after(new Date())){
            throw new InvalidArgumentException("La fecha no puede ser mayor a la actual");
        }

        pedidoBD.setFechaPedido(pedidoReq.getFechaPedido());
        pedidoBD.setCliente(pedidoReq.getCliente());
        pedidoBD.setDetallesPedidoList(pedidoReq.getDetallesPedidoList());
        pedidoBD.setCostoBruto(pedidoReq.getCostoBruto());
        pedidoBD.setCostoFinal(pedidoReq.getCostoFinal());

        return pedidoMapper.fromEntityToResponse(
                pedidoRepository.save(pedidoBD)
        );
    }

    @Override
    public void eliminarPedido(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if(pedidoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el pedido a eliminar");
        }
        pedidoRepository.delete(pedidoOpt.get());
    }
}
