package com.mpautasso.dto.prestaciones;

import com.mpautasso.dto.ImpuestosRequest;
import com.mpautasso.model.Prestacion;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {
    private Prestacion prestacion;
    private List<ImpuestosRequest> impuestosRequestList;

}
