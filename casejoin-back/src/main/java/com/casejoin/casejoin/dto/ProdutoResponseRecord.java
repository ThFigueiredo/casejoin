package com.casejoin.casejoin.dto;

import java.math.BigDecimal;

public record ProdutoResponseRecord(Long id, String nome, BigDecimal preco, Long categoriaId, String categoriaNome) {}
