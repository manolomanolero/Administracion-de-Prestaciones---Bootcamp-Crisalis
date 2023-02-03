package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue("producto")
public class Producto extends Prestacion {
    public Producto(String nombre, Double costo){
        super(nombre, costo);
    }
    public Producto(Long id, String nombre, Double costo){
        super(id, nombre, costo);
    }

    @Override
    public String getType() {
        return "producto";
    }

    @Override
    public String toString(){
        return "Producto(nombre = " + getNombre() + ", costo = " + getCosto() + ")";
    }
}
