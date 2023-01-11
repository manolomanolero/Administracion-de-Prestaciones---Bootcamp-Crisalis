package com.mpautasso.dto.impuestos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpuestoRequest {
    private Long id;
    private String nombre;
    private Double porcentaje;
}
