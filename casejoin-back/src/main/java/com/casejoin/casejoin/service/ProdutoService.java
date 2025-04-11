package com.casejoin.casejoin.service;

import com.casejoin.casejoin.dto.ProdutoRequestRecord;
import com.casejoin.casejoin.dto.ProdutoResponseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {
    ProdutoResponseRecord criar(ProdutoRequestRecord produtoRequest);
    Page<ProdutoResponseRecord> listarTodos(Pageable pageable);
    ProdutoResponseRecord buscarPorId(Long id);
    void deletar(Long id);
    void atualizar(Long id, ProdutoRequestRecord produtoRequest);
}
