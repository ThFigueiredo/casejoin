package com.casejoin.casejoin.mapper;

import com.casejoin.casejoin.dto.ProdutoRequestRecord;
import com.casejoin.casejoin.dto.ProdutoResponseRecord;
import com.casejoin.casejoin.model.Produto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "categoria.id", source = "categoriaId")
    Produto toEntity(ProdutoRequestRecord dto);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nome", target = "categoriaNome")
    ProdutoResponseRecord toResponseDTO(Produto produto);
}
