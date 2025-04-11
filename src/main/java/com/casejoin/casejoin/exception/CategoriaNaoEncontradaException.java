package com.casejoin.casejoin.exception;

import com.casejoin.casejoin.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(Long id) {
        super(Constants.CATEGORIA_NAO_ENCONTRADA + id);
    }
}
