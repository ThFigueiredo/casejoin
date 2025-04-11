package com.casejoin.casejoin.controller;

import com.casejoin.casejoin.dto.CategoriaRequestRecord;
import com.casejoin.casejoin.dto.CategoriaResponseRecord;
import com.casejoin.casejoin.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<CategoriaResponseRecord> criarCategoria(@RequestBody CategoriaRequestRecord categoriaRequestRecord) {
        return new ResponseEntity<>(categoriaService.criar(categoriaRequestRecord), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lista todas as categorias com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada")
    })
    public ResponseEntity<Page<CategoriaResponseRecord>> listarCategorias(Pageable pageable) {
        Page<CategoriaResponseRecord> categorias = categoriaService.listarTodasPaginado(pageable);
        if (categorias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma categoria por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<CategoriaResponseRecord> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma categoria pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
