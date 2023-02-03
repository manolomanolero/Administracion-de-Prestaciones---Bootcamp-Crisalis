package com.mpautasso.controller;

import com.mpautasso.dto.StringResponse;
import com.mpautasso.model.ClientesServiciosContratados;
import com.mpautasso.service.ServiciosContratadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios/contratados")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ServiciosContratadosController {
    private final ServiciosContratadosService serviciosContratadosService;

    @GetMapping
    public ResponseEntity<List<ClientesServiciosContratados>> listarServicios(){
        return ResponseEntity.ok(serviciosContratadosService.listarServicios());
    }

    @GetMapping("/search")
    public ResponseEntity<ClientesServiciosContratados> buscarServiciosPorId(@RequestParam Long id){
        return ResponseEntity.ok(serviciosContratadosService.buscarServicioPorId(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientesServiciosContratados>> buscarServiciosPorIdCliente(@RequestParam Long id){
        return ResponseEntity.ok(serviciosContratadosService.buscarServiciosPorIdCliente(id));
    }


    @GetMapping("/desactivar")
    public ResponseEntity<ClientesServiciosContratados> desactivarServicio(@RequestParam Long id){
        return ResponseEntity.ok(serviciosContratadosService.desactivarServicio(id));
    }

    @GetMapping("/activar")
    public ResponseEntity<ClientesServiciosContratados> activarServicio(@RequestParam Long id){
        return ResponseEntity.ok(serviciosContratadosService.activarServicio(id));
    }

}
