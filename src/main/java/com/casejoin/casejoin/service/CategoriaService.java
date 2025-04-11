package com.casejoin.casejoin.service;


import com.casejoin.casejoin.dto.CategoriaRequestRecord;
import com.casejoin.casejoin.dto.CategoriaResponseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoriaService {
    CategoriaResponseRecord criar(CategoriaRequestRecord categoriaRequest);

    Page<CategoriaResponseRecord> listarTodasPaginado(Pageable pageable);

    CategoriaResponseRecord buscarPorId(Long id);

    void deletar(Long id);
}
