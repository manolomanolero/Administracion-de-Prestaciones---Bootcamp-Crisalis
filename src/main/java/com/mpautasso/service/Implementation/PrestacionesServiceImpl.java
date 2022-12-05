package com.mpautasso.service.Implementation;

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

    @Override
    public List<Prestacion> listarPrestaciones() {
        return prestacionesRepository.findAll();
    }

    @Override
    public Prestacion crearPrestacion(Prestacion prestacionRequest) {
        return null;
    }


}
