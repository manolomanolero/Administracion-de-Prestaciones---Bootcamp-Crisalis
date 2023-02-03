package com.mpautasso.dto.detallesPedidos;

import com.mpautasso.dto.impuestos.ImpuestoRequest;
import com.mpautasso.dto.prestaciones.PrestacionUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesPedidoRequest {
    private Long id;
    private int cantidad;
    private PrestacionUpdateRequest prestacion;
    private int garantia;
    private boolean soporteEspecial;
    private Set<ImpuestoRequest> impuestos;
}
