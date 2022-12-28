package com.mpautasso.dto.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponse {
    private Long id;
    private String razonSocial;
    private long cuit;
}
