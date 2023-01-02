package com.mpautasso.repository;

import com.mpautasso.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByRazonSocial(String razonSocial);
}
