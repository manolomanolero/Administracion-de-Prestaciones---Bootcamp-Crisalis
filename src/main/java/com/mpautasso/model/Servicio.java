package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("servicio")
public class Servicio extends Prestacion {

    private Double cargoSoporte;

    public Servicio(String nombre, Double costo, Double cargoSoporte){
        super(nombre, costo);
        if (cargoSoporte == null){
            this.cargoSoporte = 0d;
        } else {
            this.cargoSoporte = cargoSoporte;
        }
    }
    public Servicio(Long id, String nombre, Double costo, Double cargoSoporte){
        super(id, nombre, costo);
        if (cargoSoporte == null){
            this.cargoSoporte = 0d;
        } else {
            this.cargoSoporte = cargoSoporte;
        }
    }

    @Override
    public String getType() {
        return "servicio";
    }
    @Override
    public String toString(){
        return "Servicio(nombre = " + getNombre() + ", costo = " + getCosto() + ")";
    }

    @Override
    public Double getCargoSoporte(){
        return this.cargoSoporte;
    }
}
