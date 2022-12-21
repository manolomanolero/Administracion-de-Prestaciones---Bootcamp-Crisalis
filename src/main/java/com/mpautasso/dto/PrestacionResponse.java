package com.mpautasso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrestacionResponse {
    private String nombre;
    private Double costo;
    private String tipo;
}
