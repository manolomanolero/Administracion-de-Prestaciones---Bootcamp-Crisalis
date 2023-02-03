package com.mpautasso.dto.cliente;

import com.mpautasso.dto.empresa.EmpresaResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteResponse {
    private long id;
    private long dni;
    private String nombre;
    private String apellido;
    private EmpresaResponse empresa;

    public ClienteResponse(long id, long dni, String nombre, String apellido) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
