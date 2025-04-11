package com.casejoin.casejoin.service.impl;

import com.casejoin.casejoin.dto.ProdutoRequestRecord;
import com.casejoin.casejoin.dto.ProdutoResponseRecord;
import com.casejoin.casejoin.exception.CategoriaNaoEncontradaException;
import com.casejoin.casejoin.exception.ProdutoNaoEncontradoException;
import com.casejoin.casejoin.mapper.ProdutoMapper;
import com.casejoin.casejoin.model.Categoria;
import com.casejoin.casejoin.model.Produto;
import com.casejoin.casejoin.repository.CategoriaRepository;
import com.casejoin.casejoin.repository.ProdutoRepository;
import com.casejoin.casejoin.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoServiceImpl.class);
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseRecord criar(ProdutoRequestRecord produtoRequest) {
        logger.info("Cadastrando produto: {}", produtoRequest.nome());

        Categoria categoria = categoriaRepository.findById(produtoRequest.categoriaId())
                .orElseThrow(() -> new CategoriaNaoEncontradaException(produtoRequest.categoriaId()));

        Produto produto = produtoMapper.toEntity(produtoRequest);
        produto.setCategoria(categoria);

        Produto salvo = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(salvo);
    }

    @Override
    public Page<ProdutoResponseRecord> listarTodos(Pageable pageable) {
        logger.info("Listando produtos com paginação");
        return produtoRepository.findAll(pageable).map(produtoMapper::toResponseDTO);
    }

    @Override
    public ProdutoResponseRecord buscarPorId(Long id) {
        logger.info("Buscando produto com ID: {}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return produtoMapper.toResponseDTO(produto);
    }

    @Override
    public void deletar(Long id) {
        logger.info("Deletando produto com ID: {}", id);
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public void atualizar(Long id, ProdutoRequestRecord produtoRequest) {
        logger.info("Atualizando produto com ID: {}", id);

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        Categoria categoria = categoriaRepository.findById(produtoRequest.categoriaId())
                .orElseThrow(() -> new CategoriaNaoEncontradaException(produtoRequest.categoriaId()));

        produto.setNome(produtoRequest.nome());
        produto.setPreco(produtoRequest.preco());
        produto.setCategoria(categoria);

        produtoRepository.save(produto);
        logger.info("Produto atualizado com sucesso: {}", produto.getId());
    }
}
