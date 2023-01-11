package com.mpautasso.controller;

import com.mpautasso.dto.StringResponse;
import com.mpautasso.dto.impuestos.ImpuestoRequest;
import com.mpautasso.dto.impuestos.ImpuestoResponse;
import com.mpautasso.service.ImpuestosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/impuestos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImpuestoController {
    private final ImpuestosService impuestosService;

    @GetMapping
    public ResponseEntity<List<ImpuestoResponse>> listarImpuestos(){
        return ResponseEntity.ok(impuestosService.listarImpuestos());
    }

    @GetMapping("/search")
    public ResponseEntity<ImpuestoResponse> buscarImpuesto(@RequestParam Long id){
        return ResponseEntity.ok(impuestosService.buscarImpuesto(id));
    }

    @PostMapping
    public ResponseEntity<ImpuestoResponse> agregarImpuesto(@RequestBody ImpuestoRequest impuestoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(impuestosService.agregarImpuesto(impuestoRequest));
    }

    @PutMapping
    public ResponseEntity<ImpuestoResponse> editarImpuesto(@RequestBody ImpuestoRequest impuestoRequest){
        return ResponseEntity.ok(impuestosService.editarImpuesto(impuestoRequest));
    }

    @DeleteMapping
    public ResponseEntity<StringResponse> eliminarImpuesto(@RequestParam Long id){
        impuestosService.borrarImpuesto(id);
        return ResponseEntity.ok(new StringResponse("Borrado del impuesto con exito"));
    }
}
