package com.mpautasso.Service;

import com.mpautasso.model.Prestaciones;

import java.util.List;

public interface PrestacionesService {
    List<Prestaciones> listarPrestaciones();
    Prestaciones crearPrestacion(Prestaciones prestacionesRequest);
}
