package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.ImagemSatelitalRequest;
import br.com.fiap.carbontrace.dto.response.ImagemSatelitalResponse;
import br.com.fiap.carbontrace.service.ImagemSatelitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens-satelitais")
@RequiredArgsConstructor
public class ImagemSatelitalController {

    private final ImagemSatelitalService imagemSatelitalService;

    @PostMapping
    public ResponseEntity<ImagemSatelitalResponse> cadastrar(@RequestBody @Valid ImagemSatelitalRequest request) {
        ImagemSatelitalResponse response = imagemSatelitalService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ImagemSatelitalResponse>> listarTodos() {
        List<ImagemSatelitalResponse> imagens = imagemSatelitalService.listarTodos();
        return ResponseEntity.ok(imagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagemSatelitalResponse> buscarPorId(@PathVariable Long id) {
        ImagemSatelitalResponse response = imagemSatelitalService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagemSatelitalResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ImagemSatelitalRequest request
    ) {
        ImagemSatelitalResponse response = imagemSatelitalService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        imagemSatelitalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}