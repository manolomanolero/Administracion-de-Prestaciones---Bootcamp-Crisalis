package com.mpautasso.service;

import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.model.Prestacion;
import com.mpautasso.model.Productos;

import java.util.List;

public interface PrestacionesService {
    List<Prestacion> listarPrestaciones();
    Productos crearProducto(ProductoRequest productoRequest);
}
