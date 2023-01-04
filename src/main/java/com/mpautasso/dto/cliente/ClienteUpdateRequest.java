package com.mpautasso.dto.cliente;

import com.mpautasso.dto.empresa.EmpresaRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteUpdateRequest {
    private long id;
    private long dni;
    private String nombre;
    private String apellido;
    private EmpresaRequest empresa;
}