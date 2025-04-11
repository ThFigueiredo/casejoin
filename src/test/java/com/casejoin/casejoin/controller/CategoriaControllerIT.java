package com.casejoin.casejoin.controller;

import com.casejoin.casejoin.dto.CategoriaRequestRecord;
import com.casejoin.casejoin.model.Categoria;
import com.casejoin.casejoin.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll();
    }

    @Test
    void deveCriarCategoriaComSucesso() throws Exception {
        CategoriaRequestRecord request = new CategoriaRequestRecord("Informática");

        mockMvc.perform(post("/v1/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Informática"));
    }

    @Test
    void deveListarCategoriasComPaginacao() throws Exception {
        categoriaRepository.save(Categoria.builder().nome("Beleza").build());

        mockMvc.perform(get("/v1/categorias")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.content[0].nome").value("Beleza"));
    }

    @Test
    void deveRetornar404AoBuscarCategoriaInexistente() throws Exception {
        mockMvc.perform(get("/v1/categorias/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Categoria não encontrada com ID: 999"));
    }

    @Test
    void deveDeletarCategoriaComSucesso() throws Exception {
        Categoria categoria = categoriaRepository.save(Categoria.builder().nome("Games").build());

        mockMvc.perform(delete("/v1/categorias/" + categoria.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404AoDeletarInexistente() throws Exception {
        mockMvc.perform(delete("/v1/categorias/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Categoria não encontrada com ID: 999"));
    }
}
