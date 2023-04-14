package com.porto.alurachallenge.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIException> tratarErro404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIException("Registro não encontrado!"));
    }

}
