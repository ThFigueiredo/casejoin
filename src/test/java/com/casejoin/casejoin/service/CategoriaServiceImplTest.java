package com.casejoin.casejoin.service;

import com.casejoin.casejoin.dto.CategoriaRequestRecord;
import com.casejoin.casejoin.dto.CategoriaResponseRecord;
import com.casejoin.casejoin.exception.CategoriaNaoEncontradaException;
import com.casejoin.casejoin.mapper.CategoriaMapper;
import com.casejoin.casejoin.model.Categoria;
import com.casejoin.casejoin.repository.CategoriaRepository;
import com.casejoin.casejoin.service.impl.CategoriaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCategoriaComSucesso() {
        CategoriaRequestRecord request = new CategoriaRequestRecord("Papelaria");
        Categoria categoria = Categoria.builder().nome("Papelaria").build();
        Categoria salvo = Categoria.builder().id(1L).nome("Papelaria").build();
        CategoriaResponseRecord response = new CategoriaResponseRecord(1L, "Papelaria");

        when(categoriaMapper.toEntity(request)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(salvo);
        when(categoriaMapper.toResponseDTO(salvo)).thenReturn(response);

        CategoriaResponseRecord resultado = categoriaService.criar(request);

        assertNotNull(resultado);
        assertEquals("Papelaria", resultado.nome());
        assertEquals(1L, resultado.id());
    }

    @Test
    void deveListarTodasComPaginacao() {
        Pageable pageable = PageRequest.of(0, 1);
        Categoria categoria = Categoria.builder().id(1L).nome("Alimentos").build();
        CategoriaResponseRecord dto = new CategoriaResponseRecord(1L, "Alimentos");

        when(categoriaRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(categoria)));
        when(categoriaMapper.toResponseDTO(categoria)).thenReturn(dto);

        Page<CategoriaResponseRecord> resultado = categoriaService.listarTodasPaginado(pageable);

        assertEquals(1, resultado.getTotalElements());
        assertEquals("Alimentos", resultado.getContent().get(0).nome());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExiste() {
        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.buscarPorId(999L));
    }

    @Test
    void deveDeletarComSucesso() {
        when(categoriaRepository.existsById(1L)).thenReturn(true);
        categoriaService.deletar(1L);
        verify(categoriaRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarInexistente() {
        when(categoriaRepository.existsById(99L)).thenReturn(false);
        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.deletar(99L));
    }
}
