package com.mpautasso.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequest {
    private Long id;
    private Long cuit;
    private String razonSocial;
    private Date inicioActividades;
}
