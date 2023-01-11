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
    private PrestacionResponse prestacionResponse;
    private int garantia;
    private List<ImpuestoResponse> impuestoRequest;
    private Double costoNeto;
}
