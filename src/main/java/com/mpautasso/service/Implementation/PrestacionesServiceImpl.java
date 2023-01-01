package com.mpautasso.service.Implementation;

import com.mpautasso.dto.prestaciones.PrestacionRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.dto.prestaciones.PrestacionUpdateRequest;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.PrestacionMapper;
import com.mpautasso.service.PrestacionesService;
import com.mpautasso.model.Prestacion;
import com.mpautasso.repository.PrestacionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrestacionesServiceImpl implements PrestacionesService {
    private final PrestacionesRepository prestacionesRepository;
    private final PrestacionMapper prestacionMapper;

    @Override
    public List<PrestacionResponse> listarPrestaciones() {
        var prestaciones = prestacionesRepository.findAll();
        return prestaciones.stream().map(prestacionMapper::prestacionEntityToDto).collect(Collectors.toList());
    }

    @Override
    public PrestacionResponse crearPrestacion(PrestacionRequest prestacionRequest) {
        if(prestacionesRepository.existsByNombre(prestacionRequest.getNombre())){
            throw  new InvalidArgumentException("Ya existe una prestacion de " + prestacionRequest.getNombre());
        }
        Prestacion prestacion = prestacionMapper.prestacionRequestToEntity(prestacionRequest);
        return prestacionMapper.prestacionEntityToDto(prestacionesRepository.save(prestacion));
    }

    @Override
    public PrestacionResponse buscarPrestacion(Long id) {
        Optional<Prestacion> prestacion = prestacionesRepository.findById(id);
        if(prestacion.isEmpty()){
            throw new EntityNotFoundException("No se encontró la prestación a editar");
        }
        return prestacionMapper.prestacionEntityToDto(prestacion.get());
    }

    @Override
    public PrestacionResponse actualizarPrestacion(PrestacionUpdateRequest prestacionRequest) {
        if(prestacionesRepository.existsById(prestacionRequest.getId())){
            return prestacionMapper.prestacionEntityToDto(
                    prestacionesRepository.save(
                            prestacionMapper.prestacionUpdateRequestToEntity(prestacionRequest)));
        } else {
            throw new InvalidArgumentException("No se encontro la prestacion a actualizar");
        }
    }

    @Override
    public void eliminarPrestacion(Long id) {
        Optional<Prestacion> prestacion = prestacionesRepository.findById(id);
        if(prestacion.isPresent()){
            prestacionesRepository.delete(prestacion.get());
        } else {
            throw new InvalidArgumentException("No se encontro la prestacion a borrar");
        }
    }
}
