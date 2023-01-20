package com.mpautasso.controller;

import com.mpautasso.dto.StringResponse;
import com.mpautasso.dto.pedido.PedidoRequest;
import com.mpautasso.dto.pedido.PedidoResponse;
import com.mpautasso.dto.pedido.PedidoUpdateRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos(){
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/search")
    public ResponseEntity<PedidoResponse> buscarPedido(@RequestParam Long id){
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(@RequestBody PedidoRequest pedidoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crearPedido(pedidoRequest));
    }

    @PutMapping
    public ResponseEntity<PedidoResponse> editarPedido(@RequestBody PedidoUpdateRequest pedidoRequest){
        return ResponseEntity.ok(pedidoService.editarPedido(pedidoRequest));
    }

    @DeleteMapping
    public ResponseEntity<StringResponse> eliminarPedido(@RequestParam Long id){
        pedidoService.eliminarPedido(id);
        return ResponseEntity.ok(new StringResponse("Borrado del pedido con exito"));
    }
}
