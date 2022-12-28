package com.mpautasso.service.Implementation;

import com.mpautasso.dto.empresa.EmpresaRequest;
import com.mpautasso.dto.empresa.EmpresaResponse;
import com.mpautasso.mapper.EmpresaMapper;
import com.mpautasso.model.Empresa;
import com.mpautasso.repository.EmpresaRepository;
import com.mpautasso.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    @Override
    public List<EmpresaResponse> listarEmpresas() {
        return empresaRepository.findAll().stream().map(empresaMapper::empresaEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public EmpresaResponse buscarEmpresa(String razonSocial) {
        return null;
    }

    @Override
    public EmpresaResponse crearEmpresa(EmpresaRequest empresaRequest) {
        Empresa empresa = empresaMapper.empresaRequestToEntity(empresaRequest);
        empresa = empresaRepository.save(empresa);
        return empresaMapper.empresaEntityToResponse(empresa);
    }

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).get();
    }
}
