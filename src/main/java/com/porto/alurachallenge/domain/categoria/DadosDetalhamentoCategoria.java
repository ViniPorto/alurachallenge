package com.porto.alurachallenge.domain.categoria;

public record DadosDetalhamentoCategoria(
    Long id,
    String titulo,
    Cor cor,
    boolean ativo
) {

    public DadosDetalhamentoCategoria(Categoria categoria) {
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor(), categoria.isAtivo());
    }

}
