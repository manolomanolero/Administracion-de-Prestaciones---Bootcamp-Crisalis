package com.mpautasso.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteRequest {
    private long dni;
    private String nombre;
    private String apellido;
    private long empresaId;
}
