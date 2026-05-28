package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.ImagemSatelitalRequest;
import br.com.fiap.carbontrace.dto.response.ImagemSatelitalResponse;
import br.com.fiap.carbontrace.service.ImagemSatelitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens-satelitais")
@RequiredArgsConstructor
@Tag(name = "Imagens Satelitais", description = "Endpoints para gerenciamento de imagens capturadas por satélites")
public class ImagemSatelitalController {

    private final ImagemSatelitalService imagemSatelitalService;

    @PostMapping
    @Operation(
            summary = "Cadastrar imagem satelital",
            description = "Cadastra uma imagem satelital vinculada a uma região monitorada e a um satélite existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Imagem satelital cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Região ou satélite não encontrado")
    })
    public ResponseEntity<ImagemSatelitalResponse> cadastrar(@RequestBody @Valid ImagemSatelitalRequest request) {
        ImagemSatelitalResponse response = imagemSatelitalService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar imagens satelitais",
            description = "Retorna todas as imagens satelitais cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagens satelitais listadas com sucesso")
    })
    public ResponseEntity<List<ImagemSatelitalResponse>> listarTodos() {
        List<ImagemSatelitalResponse> imagens = imagemSatelitalService.listarTodos();
        return ResponseEntity.ok(imagens);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar imagem satelital por ID",
            description = "Retorna os dados de uma imagem satelital específica pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem satelital encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem satelital não encontrada")
    })
    public ResponseEntity<ImagemSatelitalResponse> buscarPorId(@PathVariable Long id) {
        ImagemSatelitalResponse response = imagemSatelitalService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar imagem satelital",
            description = "Atualiza uma imagem satelital existente e permite alterar seus vínculos com região e satélite."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagem satelital atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Imagem satelital, região ou satélite não encontrado")
    })
    public ResponseEntity<ImagemSatelitalResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ImagemSatelitalRequest request
    ) {
        ImagemSatelitalResponse response = imagemSatelitalService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar imagem satelital",
            description = "Remove uma imagem satelital cadastrada pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagem satelital deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Imagem satelital não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        imagemSatelitalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}