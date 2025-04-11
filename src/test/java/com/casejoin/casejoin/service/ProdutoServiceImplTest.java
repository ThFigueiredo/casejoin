package com.casejoin.casejoin.service;

import com.casejoin.casejoin.dto.ProdutoRequestRecord;
import com.casejoin.casejoin.dto.ProdutoResponseRecord;
import com.casejoin.casejoin.exception.CategoriaNaoEncontradaException;
import com.casejoin.casejoin.mapper.ProdutoMapper;
import com.casejoin.casejoin.model.Categoria;
import com.casejoin.casejoin.model.Produto;
import com.casejoin.casejoin.repository.CategoriaRepository;
import com.casejoin.casejoin.repository.ProdutoRepository;
import com.casejoin.casejoin.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        ProdutoRequestRecord request = new ProdutoRequestRecord("Caneca", new BigDecimal("25.90"), 1L);
        Categoria categoria = Categoria.builder().id(1L).nome("Utilidades").build();
        Produto produto = Produto.builder().nome("Caneca").preco(new BigDecimal("25.90")).categoria(categoria).build();
        Produto salvo = Produto.builder().id(10L).nome("Caneca").preco(new BigDecimal("25.90")).categoria(categoria).build();
        ProdutoResponseRecord response = new ProdutoResponseRecord(10L, "Caneca", new BigDecimal("25.90"), 1L, "Utilidades");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(produtoMapper.toEntity(request)).thenReturn(produto);
        when(produtoRepository.save(produto)).thenReturn(salvo);
        when(produtoMapper.toResponseDTO(salvo)).thenReturn(response);

        ProdutoResponseRecord resultado = produtoService.criar(request);

        assertNotNull(resultado);
        assertEquals("Caneca", resultado.nome());
        assertEquals(1L, resultado.categoriaId());
    }

    @Test
    void deveFalharSeCategoriaNaoEncontrada() {
        ProdutoRequestRecord request = new ProdutoRequestRecord("Caneca", new BigDecimal("25.90"), 99L);
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> produtoService.criar(request));
    }

    @Test
    void deveListarTodosComPaginacao() {
        Pageable pageable = PageRequest.of(0, 2);
        Categoria cat = Categoria.builder().id(1L).nome("Geral").build();
        Produto produto = Produto.builder().id(1L).nome("Produto 1").preco(BigDecimal.TEN).categoria(cat).build();
        ProdutoResponseRecord record = new ProdutoResponseRecord(1L, "Produto 1", BigDecimal.TEN, 1L, "Geral");

        when(produtoRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(produto)));
        when(produtoMapper.toResponseDTO(produto)).thenReturn(record);

        Page<ProdutoResponseRecord> pagina = produtoService.listarTodos(pageable);

        assertEquals(1, pagina.getTotalElements());
        assertEquals("Produto 1", pagina.getContent().get(0).nome());
    }
}
