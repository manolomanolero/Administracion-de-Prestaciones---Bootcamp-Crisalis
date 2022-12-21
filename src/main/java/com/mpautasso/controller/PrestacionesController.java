package com.mpautasso.controller;

import com.mpautasso.dto.PrestacionResponse;
import com.mpautasso.dto.ProductoRequest;
import com.mpautasso.dto.ServicioRequest;
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
    public ResponseEntity<List<PrestacionResponse>> listarPrestaciones(){
        return ResponseEntity.ok(prestacionesService.listarPrestaciones());
    }

    @PostMapping("/productos")
    public ResponseEntity<PrestacionResponse> crearProducto(@RequestBody ProductoRequest productoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(prestacionesService.crearPrestacion(productoRequest));
    }
    @PostMapping("/servicios")
    public ResponseEntity<PrestacionResponse> crearServicio(@RequestBody ServicioRequest servicioRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(prestacionesService.crearPrestacion(servicioRequest));
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarPrestacion(@RequestParam String nombre){
        prestacionesService.eliminarPrestacion(nombre);
        return ResponseEntity.status(HttpStatus.OK).body("Borrado de la prestacion " + nombre +" con exito");
    }

}

