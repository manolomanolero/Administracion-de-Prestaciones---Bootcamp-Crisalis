package com.mpautasso.repository;

import com.mpautasso.model.Cliente;
import com.mpautasso.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
   @Query("SELECT cl FROM Cliente cl WHERE cl.dni = ?1 AND cl.empresa = ?2")
   Optional<Cliente> findByDniAndEmpresaId(Long dni, Empresa empresa);
}
