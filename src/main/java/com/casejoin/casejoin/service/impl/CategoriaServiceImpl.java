package com.casejoin.casejoin.service.impl;


import com.casejoin.casejoin.dto.CategoriaRequestRecord;
import com.casejoin.casejoin.dto.CategoriaResponseRecord;
import com.casejoin.casejoin.exception.CategoriaNaoEncontradaException;
import com.casejoin.casejoin.mapper.CategoriaMapper;
import com.casejoin.casejoin.model.Categoria;
import com.casejoin.casejoin.repository.CategoriaRepository;
import com.casejoin.casejoin.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaResponseRecord criar(CategoriaRequestRecord categoriaRequest) {
        logger.info("Criando nova categoria: {}", categoriaRequest.nome());
        Categoria categoria = categoriaMapper.toEntity(categoriaRequest);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaMapper.toResponseDTO(categoriaSalva);
    }

    @Override
    public Page<CategoriaResponseRecord> listarTodasPaginado(Pageable pageable) {
        logger.info("Listando categorias com paginação");
        return categoriaRepository.findAll(pageable)
                .map(categoriaMapper::toResponseDTO);
    }

    @Override
    public CategoriaResponseRecord buscarPorId(Long id) {
        logger.info("Buscando categoria por ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        return categoriaMapper.toResponseDTO(categoria);
    }

    @Override
    public void deletar(Long id) {
        logger.info("Deletando categoria com ID: {}", id);
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException(id);
        }
        categoriaRepository.deleteById(id);
    }
}
