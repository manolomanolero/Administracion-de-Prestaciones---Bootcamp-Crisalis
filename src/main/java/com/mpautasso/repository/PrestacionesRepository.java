package com.mpautasso.repository;

import com.mpautasso.model.Prestaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestacionesRepository extends JpaRepository<Prestaciones, Long> {
}
