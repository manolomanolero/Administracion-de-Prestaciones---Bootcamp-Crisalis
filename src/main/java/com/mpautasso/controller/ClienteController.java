package com.mpautasso.controller;

import com.mpautasso.dto.StringResponse;
import com.mpautasso.dto.cliente.ClienteRequest;
import com.mpautasso.dto.cliente.ClienteResponse;
import com.mpautasso.dto.cliente.ClienteUpdateRequest;
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

    @GetMapping("/search")
    public ResponseEntity<ClienteResponse> buscarCliente(@RequestParam Long id){
        return ResponseEntity.ok(clientesService.buscarCliente(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequest clienteRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.crearCliente(clienteRequest));
    }

    @PutMapping
    public ResponseEntity<ClienteResponse> actualizarCliente(@RequestBody ClienteUpdateRequest clienteRequest){
        return ResponseEntity.ok(clientesService.editarCliente(clienteRequest));
    }

    @DeleteMapping
    public ResponseEntity<StringResponse> eliminarCliente(@RequestParam Long id){
        clientesService.eliminarCliente(id);
        return ResponseEntity.ok(new StringResponse("Borrado del Cliente con exito"));
    }


    @GetMapping("/empresas")
    public ResponseEntity<List<EmpresaResponse>> listarEmpresas(){
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @GetMapping("/empresas/search")
    public ResponseEntity<EmpresaResponse> buscarEmpresa(@RequestParam Long id){
        return ResponseEntity.ok(empresaService.buscarEmpresaPorId(id));
    }

    @PostMapping("/empresas")
    public ResponseEntity<EmpresaResponse> crearEmpresa(@RequestBody EmpresaRequest empresaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.crearEmpresa(empresaRequest));
    }

    @PutMapping("/empresas")
    public ResponseEntity<EmpresaResponse> actualizarEmpresa(@RequestBody EmpresaRequest empresaRequest) {
        return ResponseEntity.ok(empresaService.editarEmpresa(empresaRequest));
    }

    @DeleteMapping("/empresas")
    public ResponseEntity<StringResponse> eliminarEmpresa(@RequestParam Long id){
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.ok(new StringResponse("Borrado de la Empresa con exito"));
    }
}
