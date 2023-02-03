package com.mpautasso.controller;

import com.mpautasso.dto.StringResponse;
import com.mpautasso.dto.prestaciones.PrestacionRequest;
import com.mpautasso.dto.prestaciones.PrestacionResponse;
import com.mpautasso.dto.prestaciones.PrestacionUpdateRequest;
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

    @GetMapping("/search")
    public ResponseEntity<PrestacionResponse> buscarPrestacion(@RequestParam Long id){
        return ResponseEntity.ok(prestacionesService.buscarPrestacion(id));
    }

    @PostMapping
    public ResponseEntity<PrestacionResponse> crearPrestacion(@RequestBody PrestacionRequest prestacionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(prestacionesService.crearPrestacion(prestacionRequest));
    }

    @PutMapping
    public ResponseEntity<PrestacionResponse> actualizarPrestacion(@RequestBody PrestacionUpdateRequest prestacionRequest){
        return ResponseEntity.ok(prestacionesService.actualizarPrestacion(prestacionRequest));
    }

    @DeleteMapping
    public ResponseEntity<StringResponse> eliminarPrestacion(@RequestParam Long id){
        prestacionesService.eliminarPrestacion(id);
        return ResponseEntity.ok(new StringResponse("Borrado de la prestacion con exito"));
    }

}

