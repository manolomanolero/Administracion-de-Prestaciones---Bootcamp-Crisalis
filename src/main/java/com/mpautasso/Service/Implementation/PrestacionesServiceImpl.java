package com.mpautasso.Service.Implementation;

import com.mpautasso.Service.PrestacionesService;
import com.mpautasso.model.Prestaciones;
import com.mpautasso.repository.PrestacionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrestacionesServiceImpl implements PrestacionesService {
    private final PrestacionesRepository prestacionesRepository;

    @Override
    public List<Prestaciones> listarPrestaciones() {
        return prestacionesRepository.findAll();
    }

    @Override
    public Prestaciones crearPrestacion(Prestaciones prestacionesRequest) {
        return null;
    }


}
