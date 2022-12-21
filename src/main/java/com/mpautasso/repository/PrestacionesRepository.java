package com.mpautasso.repository;

import com.mpautasso.model.Prestacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrestacionesRepository extends JpaRepository<Prestacion, Long> {
    Optional<Prestacion> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
