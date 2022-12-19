package com.mpautasso.controller;

import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.model.Prestacion;
import com.mpautasso.service.PrestacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestaciones")
@RequiredArgsConstructor
public class PrestacionesController {
    private final PrestacionesService prestacionesService;

    @GetMapping
    public ResponseEntity<List<Prestacion>> listarPrestaciones(){
        return ResponseEntity.ok(prestacionesService.listarPrestaciones());
    }

    @PostMapping("/productos")
    public ResponseEntity<Prestacion> crearProducto(@RequestBody ProductoRequest productoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(prestacionesService.crearProducto(productoRequest));
    }
}
