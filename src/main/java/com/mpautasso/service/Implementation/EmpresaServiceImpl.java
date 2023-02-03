package com.mpautasso.service.Implementation;

import com.mpautasso.dto.empresa.EmpresaRequest;
import com.mpautasso.dto.empresa.EmpresaResponse;
import com.mpautasso.exception.EntityNotFoundException;
import com.mpautasso.exception.InvalidArgumentException;
import com.mpautasso.mapper.EmpresaMapper;
import com.mpautasso.model.Empresa;
import com.mpautasso.repository.EmpresaRepository;
import com.mpautasso.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Empresa> empresaOpt = empresaRepository.findByRazonSocial(razonSocial);
        if(empresaOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontró empresa con razon social '" + razonSocial + "'");
        }
        return empresaMapper.empresaEntityToResponse(empresaOpt.get());
    }

    @Override
    public EmpresaResponse crearEmpresa(EmpresaRequest empresaRequest) {
        Empresa empresa = empresaMapper.empresaRequestToEntity(empresaRequest);
        return empresaMapper.empresaEntityToResponse(empresaRepository.save(empresa));
    }

    @Override
    public EmpresaResponse editarEmpresa(EmpresaRequest empresaRequest) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaRequest.getId());
        if(empresaOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontró empresa a actualizar");
        }
        return empresaMapper.empresaEntityToResponse(
                empresaRepository.save(empresaMapper.empresaRequestToEntity(empresaRequest)));
    }

    @Override
    public EmpresaResponse buscarEmpresaPorId(Long id) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(id);
        if(empresaOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontró empresa con el id");
        }
        return empresaMapper.empresaEntityToResponse(empresaOpt.get());
    }

    @Override
    public void eliminarEmpresa(Long id) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(id);
        if(empresaOpt.isPresent()){
            empresaRepository.delete(empresaOpt.get());
        } else {
            throw new InvalidArgumentException("No se encontro la prestacion a borrar");
        }
    }
}
