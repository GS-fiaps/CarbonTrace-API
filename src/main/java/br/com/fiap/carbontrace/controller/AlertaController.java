package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.AlertaRequest;
import br.com.fiap.carbontrace.dto.response.AlertaResponse;
import br.com.fiap.carbontrace.service.AlertaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;

    @PostMapping
    public ResponseEntity<AlertaResponse> cadastrar(@RequestBody @Valid AlertaRequest request) {
        AlertaResponse response = alertaService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AlertaResponse>> listarTodos() {
        List<AlertaResponse> alertas = alertaService.listarTodos();
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> buscarPorId(@PathVariable Long id) {
        AlertaResponse response = alertaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlertaRequest request
    ) {
        AlertaResponse response = alertaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}