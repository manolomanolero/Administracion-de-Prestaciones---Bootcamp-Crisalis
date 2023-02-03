package com.mpautasso.service;

import com.mpautasso.dto.prestaciones.PrestacionRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.dto.prestaciones.PrestacionUpdateRequest;


import java.util.List;

public interface PrestacionesService {
    List<PrestacionResponse> listarPrestaciones();
    PrestacionResponse crearPrestacion(PrestacionRequest prestacionRequest);
    PrestacionResponse buscarPrestacion(Long id);
    PrestacionResponse actualizarPrestacion(PrestacionUpdateRequest prestacionRequest);
    void eliminarPrestacion(Long id);
    boolean existePrestacion(Long id);
}
