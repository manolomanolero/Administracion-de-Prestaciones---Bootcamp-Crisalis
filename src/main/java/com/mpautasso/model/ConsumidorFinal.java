package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("2")
public class ConsumidorFinal extends Cliente {

    public ConsumidorFinal(Long dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
    }

    public ConsumidorFinal(Long id, Long dni, String nombre, String apellido) {
        super(id, dni, nombre, apellido);
    }

    @Override
    public String getType() {
        return "Consumidor final";
    }
}
