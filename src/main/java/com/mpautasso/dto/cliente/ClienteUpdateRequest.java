package com.mpautasso.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteUpdateRequest {
    private long id;
    private long dni;
    private String nombre;
    private String apellido;
    private long empresaId;
}