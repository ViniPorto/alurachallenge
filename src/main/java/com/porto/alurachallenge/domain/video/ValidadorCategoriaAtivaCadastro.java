package com.porto.alurachallenge.domain.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.porto.alurachallenge.domain.categoria.CategoriaRepository;
import com.porto.alurachallenge.infra.exception.APIException;

@Component
public class ValidadorCategoriaAtivaCadastro implements ValidadorCadastroDeVideo {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void validar(DadosCadastroVideo dados) {
        if(dados.categoriaId() == null) return; //não é obrigatório informar id da categoria na criação do vídeo
        var categoriaEstaAtiva = categoriaRepository.findAtivoById(dados.categoriaId());
        if(!categoriaEstaAtiva) throw new APIException("Categoria inativa!");
    }
    
}
