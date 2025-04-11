package com.casejoin.casejoin.dto;

import java.math.BigDecimal;

public record ProdutoRequestRecord(String nome, BigDecimal preco, Long categoriaId) {}
