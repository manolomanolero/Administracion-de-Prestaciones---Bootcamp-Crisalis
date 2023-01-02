package com.mpautasso.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequest {
    private Long id;
    private Long cuit;
    private String razonSocial;
}
