package br.com.jwprogammer.avalicacao.exception;

import lombok.Getter;

public class ErroComunicacaoException extends Exception{

    @Getter
    private Integer httpError;
    public ErroComunicacaoException(String msg, Integer status) {
        super(msg);
        this.httpError = status;
    }
}
