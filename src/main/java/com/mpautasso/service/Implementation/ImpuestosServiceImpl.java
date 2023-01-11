package com.mpautasso.service.Implementation;

import com.mpautasso.dto.impuestos.ImpuestoRequest;
import com.mpautasso.dto.impuestos.ImpuestoResponse;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.ImpuestosMapper;
import com.mpautasso.model.Impuesto;
import com.mpautasso.repository.ImpuestosRepository;
import com.mpautasso.service.ImpuestosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImpuestosServiceImpl implements ImpuestosService {
    private final ImpuestosRepository impuestosRepository;
    private final ImpuestosMapper impuestosMapper;
    @Override
    public List<ImpuestoResponse> listarImpuestos() {
        return impuestosRepository.findAll().stream()
                .map(impuestosMapper::fromEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public ImpuestoResponse buscarImpuesto(Long id) {
        Optional<Impuesto> impuestoOpt = impuestosRepository.findById(id);
        if(impuestoOpt.isEmpty()){
            throw new EntityNotFoundException("El impuesto no fue encontrado en el servidor");
        }
        return impuestosMapper.fromEntityToResponse(impuestoOpt.get());
    }

    @Override
    public ImpuestoResponse agregarImpuesto(ImpuestoRequest impuestoRequest) {
        if(impuestoRequest.getPorcentaje() <= 0){
            throw new InvalidArgumentException("El porcentaje del impuesto no puede ser negativo o 0");
        }
        if(impuestoRequest.getNombre().isBlank()){
            throw new InvalidArgumentException("El nombre del impuesto debe ser valido");
        }
        return impuestosMapper.fromEntityToResponse(
                impuestosRepository.save(
                        impuestosMapper.fromImpuestoRequestToEntity(impuestoRequest)
                )
        );
    }

    @Override
    public ImpuestoResponse editarImpuesto(ImpuestoRequest impuestoRequest) {
        Optional<Impuesto> impuestoBDOpt = impuestosRepository.findById(impuestoRequest.getId());
        if(impuestoBDOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontró el impuesto a actualizar");
        }
        if(impuestoRequest.getPorcentaje() <= 0){
            throw new InvalidArgumentException("El porcentaje del impuesto no puede ser negativo o 0");
        }
        if(impuestoRequest.getNombre().isBlank()){
            throw new InvalidArgumentException("El nombre del impuesto debe ser valido");
        }
        Impuesto impuestoBD = impuestoBDOpt.get();
        impuestoBD.setNombre(impuestoRequest.getNombre());
        impuestoBD.setPorcentaje(impuestoRequest.getPorcentaje());
        return impuestosMapper.fromEntityToResponse(
                    impuestosRepository.save(impuestoBD)
        );
    }

    @Override
    public void borrarImpuesto(Long id) {
        if(impuestosRepository.existsById(id)){
            throw new EntityNotFoundException("No se encontro el impuesto a eliminar");
        }
        impuestosRepository.deleteById(id);
    }
}
