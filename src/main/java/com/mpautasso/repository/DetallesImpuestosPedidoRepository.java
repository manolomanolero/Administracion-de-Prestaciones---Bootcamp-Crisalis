package com.mpautasso.repository;

import com.mpautasso.model.DetallesImpuestosPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesImpuestosPedidoRepository extends JpaRepository<DetallesImpuestosPedido, Long> {
}
