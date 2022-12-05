package com.mpautasso.dto;

import com.mpautasso.model.Prestacion;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {
    private Prestacion prestacion;
    private List<ImpuestosRequest> impuestosRequestList;

}
