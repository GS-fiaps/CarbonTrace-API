package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.OcorrenciaRequest;
import br.com.fiap.carbontrace.dto.response.OcorrenciaResponse;
import br.com.fiap.carbontrace.service.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<OcorrenciaResponse> cadastrar(@RequestBody @Valid OcorrenciaRequest request) {
        OcorrenciaResponse response = ocorrenciaService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaResponse>> listarTodos() {
        List<OcorrenciaResponse> ocorrencias = ocorrenciaService.listarTodos();
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaResponse> buscarPorId(@PathVariable Long id) {
        OcorrenciaResponse response = ocorrenciaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid OcorrenciaRequest request
    ) {
        OcorrenciaResponse response = ocorrenciaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ocorrenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}