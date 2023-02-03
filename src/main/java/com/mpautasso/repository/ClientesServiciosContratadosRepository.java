package com.mpautasso.repository;

import com.mpautasso.model.Cliente;
import com.mpautasso.model.ClientesServiciosContratados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesServiciosContratadosRepository extends JpaRepository<ClientesServiciosContratados, Long> {
    List<ClientesServiciosContratados> findAllByCliente(Cliente cliente);

    boolean existsByClienteAndAndEstaActivo(Cliente cliente, Boolean estaActivo);
}
