package com.porto.alurachallenge.domain.video;

public record DadosDetalhamentoVideo(
    Long id,
    String titulo,
    String descricao,
    String url,
    boolean ativo
) {

    public DadosDetalhamentoVideo(Video video) {
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.isAtivo());
    }

}
