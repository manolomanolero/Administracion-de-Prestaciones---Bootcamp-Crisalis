package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue("producto")
public class Productos extends Prestacion {
    public Productos(String nombre, Double costo){
        super(nombre, costo);
    }

    @Override
    public String getType() {
        return "Producto";
    }

    @Override
    public String toString(){
        return "Producto(nombre = " + getNombre() + ", costo = " + getCosto() + ")";
    }
}
