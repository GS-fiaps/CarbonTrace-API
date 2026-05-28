package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.SateliteRequest;
import br.com.fiap.carbontrace.dto.response.SateliteResponse;
import br.com.fiap.carbontrace.service.SateliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/satelites")
@RequiredArgsConstructor
public class SateliteController {

    private final SateliteService sateliteService;

    @PostMapping
    public ResponseEntity<SateliteResponse> cadastrar(@RequestBody @Valid SateliteRequest request) {
        SateliteResponse response = sateliteService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SateliteResponse>> listarTodos() {
        List<SateliteResponse> satelites = sateliteService.listarTodos();
        return ResponseEntity.ok(satelites);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SateliteResponse> buscarPorId(@PathVariable Long id) {
        SateliteResponse response = sateliteService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SateliteResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SateliteRequest request
    ) {
        SateliteResponse response = sateliteService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sateliteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}