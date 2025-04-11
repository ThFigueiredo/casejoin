package com.casejoin.casejoin.exception;

import com.casejoin.casejoin.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id) {
        super(Constants.PRODUTO_NAO_ENCONTRADO + id);
    }
}
