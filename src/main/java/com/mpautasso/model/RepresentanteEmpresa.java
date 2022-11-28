package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@DiscriminatorValue("not null")
public class RepresentanteEmpresa extends Clientes{
    /* TODO: resolver incompatibilidad con discriminator value. Doble mapeo de columna
    @Column(name = "empresa_id")
    private Long empresaId;*/
}
