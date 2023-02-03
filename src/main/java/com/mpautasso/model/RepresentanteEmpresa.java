package com.mpautasso.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("1")
public class RepresentanteEmpresa extends Cliente {
    public RepresentanteEmpresa(Long dni, String nombre, String apellido, Empresa empresa) {
        super(dni, nombre, apellido, empresa);
    }

    public RepresentanteEmpresa(Long id, Long dni, String nombre, String apellido, Empresa empresa) {
        super(id, dni, nombre, apellido, empresa);
    }

    @Override
    public String getType() {
        return "Representante empresa";
    }
}
