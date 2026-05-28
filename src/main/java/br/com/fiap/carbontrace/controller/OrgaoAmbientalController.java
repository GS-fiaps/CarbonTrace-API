package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.OrgaoAmbientalRequest;
import br.com.fiap.carbontrace.dto.response.OrgaoAmbientalResponse;
import br.com.fiap.carbontrace.service.OrgaoAmbientalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orgaos-ambientais")
@RequiredArgsConstructor
public class OrgaoAmbientalController {

    private final OrgaoAmbientalService orgaoAmbientalService;

    @PostMapping
    public ResponseEntity<OrgaoAmbientalResponse> cadastrar(@RequestBody @Valid OrgaoAmbientalRequest request) {
        OrgaoAmbientalResponse response = orgaoAmbientalService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrgaoAmbientalResponse>> listarTodos() {
        List<OrgaoAmbientalResponse> orgaos = orgaoAmbientalService.listarTodos();
        return ResponseEntity.ok(orgaos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrgaoAmbientalResponse> buscarPorId(@PathVariable Long id) {
        OrgaoAmbientalResponse response = orgaoAmbientalService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrgaoAmbientalResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid OrgaoAmbientalRequest request
    ) {
        OrgaoAmbientalResponse response = orgaoAmbientalService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        orgaoAmbientalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}