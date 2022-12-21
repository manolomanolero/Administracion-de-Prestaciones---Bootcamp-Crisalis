package com.mpautasso.service.Implementation;

import com.mpautasso.dto.PrestacionResponse;
import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.dto.ServicioRequest;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.PrestacionMapper;
import com.mpautasso.model.Productos;
import com.mpautasso.model.Servicios;
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
        System.out.println(prestaciones);
        return prestaciones.stream().map(prestacionMapper::prestacionEntityToDto).collect(Collectors.toList());
    }

    @Override
    public PrestacionResponse crearPrestacion(ProductoRequest productoRequest) {
        Productos producto = prestacionMapper.productoRequestToEntity(productoRequest);
        return prestacionMapper.prestacionEntityToDto(prestacionesRepository.save(producto));
    }

    @Override
    public PrestacionResponse crearPrestacion(ServicioRequest servicioRequest) {
        Servicios servicio = prestacionMapper.servicioRequestToEntity(servicioRequest);
        return prestacionMapper.prestacionEntityToDto(prestacionesRepository.save(servicio));
    }

    @Override
    public void eliminarPrestacion(String nombrePrestacion) {
        Optional<Prestacion> prestacion = prestacionesRepository.findByNombre(nombrePrestacion);
        if(prestacion.isPresent()){
            prestacionesRepository.delete(prestacion.get());
        } else {
            throw new InvalidArgumentException("No se encontro la prestacion a borrar");
        }
    }

}
