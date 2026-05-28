package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.AnaliseRequest;
import br.com.fiap.carbontrace.dto.response.AnaliseResponse;
import br.com.fiap.carbontrace.service.AnaliseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analises")
@RequiredArgsConstructor
public class AnaliseController {

    private final AnaliseService analiseService;

    @PostMapping
    public ResponseEntity<AnaliseResponse> cadastrar(@RequestBody @Valid AnaliseRequest request) {
        AnaliseResponse response = analiseService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AnaliseResponse>> listarTodos() {
        List<AnaliseResponse> analises = analiseService.listarTodos();
        return ResponseEntity.ok(analises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnaliseResponse> buscarPorId(@PathVariable Long id) {
        AnaliseResponse response = analiseService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnaliseResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AnaliseRequest request
    ) {
        AnaliseResponse response = analiseService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        analiseService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}