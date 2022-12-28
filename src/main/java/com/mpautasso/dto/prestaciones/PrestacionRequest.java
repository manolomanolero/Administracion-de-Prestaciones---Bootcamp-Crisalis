package com.mpautasso.dto.prestaciones;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrestacionRequest {
    private String nombre;
    private Double costo;
    private String tipo;
}
