package com.mpautasso.dto.prestaciones;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrestacionResponse {
    private Long id;
    private String nombre;
    private Double costo;
    private String tipo;
}
