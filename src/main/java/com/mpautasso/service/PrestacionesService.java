package com.mpautasso.service;

import com.mpautasso.dto.prestaciones.PrestacionRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;


import java.util.List;

public interface PrestacionesService {
    List<PrestacionResponse> listarPrestaciones();
    PrestacionResponse crearPrestacion(PrestacionRequest prestacionRequest);
    PrestacionResponse actualizarPrestacion(PrestacionRequest prestacionRequest);
    void eliminarPrestacion(String nombrePrestacion);
}
