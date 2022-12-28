package com.mpautasso.service;

import com.mpautasso.dto.empresa.EmpresaRequest;
import com.mpautasso.dto.empresa.EmpresaResponse;
import com.mpautasso.model.Empresa;

import java.util.List;

public interface EmpresaService {
    List<EmpresaResponse> listarEmpresas();
    EmpresaResponse buscarEmpresa(String razonSocial);
    EmpresaResponse crearEmpresa(EmpresaRequest empresaRequest);
    Empresa buscarEmpresaPorId(Long id);
}
