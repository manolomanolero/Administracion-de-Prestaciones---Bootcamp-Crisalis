package com.mpautasso.controller;

import com.mpautasso.dto.prestaciones.PrestacionRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.service.PrestacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestaciones")
@RequiredArgsConstructor
@CrossOrigin
public class PrestacionesController {
    private final PrestacionesService prestacionesService;

    @GetMapping
    public ResponseEntity<List<PrestacionResponse>> listarPrestaciones(){
        return ResponseEntity.ok(prestacionesService.listarPrestaciones());
    }

    @PostMapping
    public ResponseEntity<PrestacionResponse> crearPrestacion(@RequestBody PrestacionRequest prestacionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(prestacionesService.crearPrestacion(prestacionRequest));
    }

    @PutMapping
    public ResponseEntity<PrestacionResponse> actualizarPrestacion(@RequestBody PrestacionRequest prestacionRequest){
        return ResponseEntity.status(HttpStatus.OK).body(prestacionesService.crearPrestacion(prestacionRequest));
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarPrestacion(@RequestParam String nombre){
        prestacionesService.eliminarPrestacion(nombre);
        return ResponseEntity.status(HttpStatus.OK).body("Borrado de la prestacion " + nombre +" con exito");
    }

}

