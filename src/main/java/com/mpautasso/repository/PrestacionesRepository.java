package com.mpautasso.repository;

import com.mpautasso.model.Prestacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestacionesRepository extends JpaRepository<Prestacion, Long> {
}
