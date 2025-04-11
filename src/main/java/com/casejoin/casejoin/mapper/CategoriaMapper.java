package com.casejoin.casejoin.mapper;


import com.casejoin.casejoin.dto.CategoriaRequestRecord;
import com.casejoin.casejoin.dto.CategoriaResponseRecord;
import com.casejoin.casejoin.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toEntity(CategoriaRequestRecord dto);

    CategoriaResponseRecord toResponseDTO(Categoria categoria);
}
