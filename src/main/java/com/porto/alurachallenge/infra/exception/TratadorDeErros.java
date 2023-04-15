package com.porto.alurachallenge.infra.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.porto.alurachallenge.domain.categoria.Cor;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DadosAPIException> tratarErro404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosAPIException("Registro não encontrado!"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<DadosAPIException> tratarErroAPIException(APIException ex){
        return ResponseEntity.badRequest().body(new DadosAPIException(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DadosAPIException> tratarErroEnumIncorreto(){
        return ResponseEntity.badRequest().body(new DadosAPIException("Cor não definida para os valores aceitos: " + Arrays.asList(Cor.values())));
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
