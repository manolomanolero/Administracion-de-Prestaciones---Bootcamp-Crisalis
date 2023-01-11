package com.mpautasso.service;

import com.mpautasso.dto.impuestos.ImpuestoRequest;
import com.mpautasso.dto.impuestos.ImpuestoResponse;

import java.util.List;

public interface ImpuestosService {
    List<ImpuestoResponse> listarImpuestos();
    ImpuestoResponse buscarImpuesto(Long id);
    ImpuestoResponse agregarImpuesto(ImpuestoRequest impuestoRequest);
    ImpuestoResponse editarImpuesto(ImpuestoRequest impuestoRequest);
    void borrarImpuesto(Long id);
}
