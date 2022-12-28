package com.mpautasso.controller;

import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.empresa.EmpresaRequest;
import com.mpautasso.dto.empresa.EmpresaResponse;
import com.mpautasso.service.ClientesService;
import com.mpautasso.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
@CrossOrigin("*")
public class ClienteController {
    private final ClientesService clientesService;
    private final EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes(){
        return ResponseEntity.ok(clientesService.listarClientes());
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequest clienteRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.crearCliente(clienteRequest));
    }


    @GetMapping("/empresas")
    public ResponseEntity<List<EmpresaResponse>> listarEmpresas(){
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @PostMapping("/empresas")
    public ResponseEntity<EmpresaResponse> crearEmpresa(@RequestBody EmpresaRequest empresaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.crearEmpresa(empresaRequest));
    }
}
