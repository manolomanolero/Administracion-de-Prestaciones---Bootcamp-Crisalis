package com.mpautasso.repository;

import com.mpautasso.model.DetallesPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallesPedidoRepository extends JpaRepository<DetallesPedido, Long> {
}
