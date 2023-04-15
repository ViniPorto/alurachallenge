package com.porto.alurachallenge.infra.exception;

public class APIException extends RuntimeException {

    public APIException(String mensagem) {
        super(mensagem);
    }
    
}
