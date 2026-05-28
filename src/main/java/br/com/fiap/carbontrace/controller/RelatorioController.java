package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.RelatorioRequest;
import br.com.fiap.carbontrace.dto.response.RelatorioResponse;
import br.com.fiap.carbontrace.service.RelatorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping
    public ResponseEntity<RelatorioResponse> cadastrar(@RequestBody @Valid RelatorioRequest request) {
        RelatorioResponse response = relatorioService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioResponse>> listarTodos() {
        List<RelatorioResponse> relatorios = relatorioService.listarTodos();
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioResponse> buscarPorId(@PathVariable Long id) {
        RelatorioResponse response = relatorioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RelatorioRequest request
    ) {
        RelatorioResponse response = relatorioService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}