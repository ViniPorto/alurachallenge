package com.porto.alurachallenge.domain.categoria;

public record DadosListagemCategoria(
    Long id,
    String titulo,
    Cor cor
) {

    public DadosListagemCategoria(Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }

}
