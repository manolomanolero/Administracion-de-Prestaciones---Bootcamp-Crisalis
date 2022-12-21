package com.mpautasso.service;

import com.mpautasso.dto.PrestacionResponse;
import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.dto.ServicioRequest;


import java.util.List;

public interface PrestacionesService {
    List<PrestacionResponse> listarPrestaciones();
    PrestacionResponse crearPrestacion(ProductoRequest productoRequest);
    PrestacionResponse crearPrestacion(ServicioRequest productoRequest);
    void eliminarPrestacion(String nombrePrestacion);
}
