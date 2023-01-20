package com.mpautasso.dto.detallesPedidos;

import com.mpautasso.dto.impuestos.ImpuestoRequest;
import com.mpautasso.dto.prestaciones.PrestacionUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesPedidoRequest {
    private Long id;
    private int cantidad;
    private PrestacionUpdateRequest prestacion;
    private int garantia;
    private List<ImpuestoRequest> impuestos;
}
