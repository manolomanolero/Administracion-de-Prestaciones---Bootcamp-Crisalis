package com.mpautasso.service.Implementation;

import com.mpautasso.dto.detallesPedidos.DetallesPedidoRequest;
import com.mpautasso.dto.detallesPedidos.DetallesPedidoResponse;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.DetallesPedidoMapper;
import com.mpautasso.model.DetallesPedido;
import com.mpautasso.repository.DetallesPedidoRepository;
import com.mpautasso.service.DetallesPedidosService;
import com.mpautasso.service.PrestacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallesPedidosServiceImpl implements DetallesPedidosService {
    private final DetallesPedidoRepository detallesRepository;
    private final PrestacionesService prestacionesService;
    private final DetallesPedidoMapper detallesMapper;

    @Override
    public List<DetallesPedidoResponse> findAll() {
        return detallesRepository.findAll().stream()
                    .map(detallesMapper::entityToResponse)
                    .collect(Collectors.toList());
    }

    @Override
    public List<DetallesPedidoResponse> findAllByPedidoId(Long id) {
        return null;
    }

    @Override
    public DetallesPedidoResponse findById(Long id) {
        Optional<DetallesPedido> detallesOpt = detallesRepository.findById(id);
        if(detallesOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el detalle de pedido");
        }
        return detallesMapper.entityToResponse(detallesOpt.get());
    }

    @Override
    public DetallesPedidoResponse create(DetallesPedidoRequest detallePedidoRequest) {
        if(detallePedidoRequest.getCantidad() <= 0){
            throw new InvalidArgumentException("La cantidad de la prestación debe ser válida");
        }
        if(!prestacionesService.existePrestacion(detallePedidoRequest.getPrestacionRequest().getId())){
            throw new InvalidArgumentException("La prestacion no se ha encontrado.");
        }

        return null;
    }

    @Override
    public DetallesPedidoResponse update(DetallesPedidoRequest detallesPedidoRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
