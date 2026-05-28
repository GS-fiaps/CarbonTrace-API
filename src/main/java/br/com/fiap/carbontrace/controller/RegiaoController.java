package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.RegiaoRequest;
import br.com.fiap.carbontrace.dto.response.RegiaoResponse;
import br.com.fiap.carbontrace.service.RegiaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regioes")
@RequiredArgsConstructor
public class RegiaoController {

    private final RegiaoService regiaoService;

    @PostMapping
    public ResponseEntity<RegiaoResponse> cadastrar(@RequestBody @Valid RegiaoRequest request) {
        RegiaoResponse response = regiaoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RegiaoResponse>> listarTodos() {
        List<RegiaoResponse> regioes = regiaoService.listarTodos();
        return ResponseEntity.ok(regioes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoResponse> buscarPorId(@PathVariable Long id) {
        RegiaoResponse response = regiaoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RegiaoRequest request
    ) {
        RegiaoResponse response = regiaoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        regiaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}