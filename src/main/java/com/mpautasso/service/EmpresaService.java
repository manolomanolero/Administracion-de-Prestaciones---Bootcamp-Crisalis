package com.mpautasso.service;

import com.mpautasso.dto.empresa.EmpresaRequest;
import com.mpautasso.dto.empresa.EmpresaResponse;

import java.util.List;

public interface EmpresaService {
    List<EmpresaResponse> listarEmpresas();
    EmpresaResponse buscarEmpresa(String razonSocial);
    EmpresaResponse crearEmpresa(EmpresaRequest empresaRequest);
    EmpresaResponse editarEmpresa(EmpresaRequest empresaRequest);
    EmpresaResponse buscarEmpresaPorId(Long id);
    void eliminarEmpresa(Long id);
}
