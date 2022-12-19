package com.mpautasso.service.Implementation;

import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.mapper.PrestacionMapper;
import com.mpautasso.model.Productos;
import com.mpautasso.service.PrestacionesService;
import com.mpautasso.model.Prestacion;
import com.mpautasso.repository.PrestacionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrestacionesServiceImpl implements PrestacionesService {
    private final PrestacionesRepository prestacionesRepository;
    private final PrestacionMapper prestacionMapper;

    @Override
    public List<Prestacion> listarPrestaciones() {
        return prestacionesRepository.findAll();
    }

    @Override
    public Productos crearProducto(ProductoRequest productoRequest) {
        Productos producto = prestacionMapper.productoRequestToEntity(productoRequest);

        return producto;
        //return prestacionesRepository.save(producto);
    }


}
