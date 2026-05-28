package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.EstadoRequest;
import br.com.fiap.carbontrace.dto.response.EstadoResponse;
import br.com.fiap.carbontrace.service.EstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @PostMapping
    public ResponseEntity<EstadoResponse> cadastrar(@RequestBody @Valid EstadoRequest request) {
        EstadoResponse response = estadoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EstadoResponse>> listarTodos() {
        List<EstadoResponse> estados = estadoService.listarTodos();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponse> buscarPorId(@PathVariable Long id) {
        EstadoResponse response = estadoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EstadoRequest request
    ) {
        EstadoResponse response = estadoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}