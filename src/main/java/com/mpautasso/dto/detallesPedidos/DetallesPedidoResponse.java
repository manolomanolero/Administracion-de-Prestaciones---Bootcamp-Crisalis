package com.mpautasso.dto.detallesPedidos;

import com.mpautasso.dto.impuestos.ImpuestoResponse;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesPedidoResponse {
    private Long id;
    private int cantidad;
    private PrestacionResponse prestacion;
    private int garantia;
    private List<ImpuestoResponse> impuestos;
    private Double costoProducto;
    private Double costoNeto;
}
