package com.casejoin.casejoin.controller;

import com.casejoin.casejoin.dto.ProdutoRequestRecord;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProdutoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long categoriaId;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll();
        Categoria categoria = Categoria.builder().nome("Escritório").build();
        categoria = categoriaRepository.save(categoria);
        categoriaId = categoria.getId();
    }

    @Test
    void deveCriarProdutoComSucesso() throws Exception {
        ProdutoRequestRecord request = new ProdutoRequestRecord("Cadeira", new BigDecimal("399.90"), categoriaId);

        mockMvc.perform(post("/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Cadeira"))
                .andExpect(jsonPath("$.preco").value(399.90));
    }

    @Test
    void deveRetornarErroQuandoCategoriaNaoExiste() throws Exception {
        ProdutoRequestRecord request = new ProdutoRequestRecord("Lâmpada", new BigDecimal("12.50"), 999L);

        mockMvc.perform(post("/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Categoria não encontrada com ID: 999"));
    }
}
