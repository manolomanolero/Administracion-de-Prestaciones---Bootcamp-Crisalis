package com.mpautasso.repository;

import com.mpautasso.model.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpuestosRepository extends JpaRepository<Impuesto, Long> {
}
