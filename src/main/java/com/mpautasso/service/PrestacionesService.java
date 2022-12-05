package com.mpautasso.service;

import com.mpautasso.model.Prestacion;

import java.util.List;

public interface PrestacionesService {
    List<Prestacion> listarPrestaciones();
    Prestacion crearPrestacion(Prestacion prestacionRequest);
}
