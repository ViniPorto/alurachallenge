package com.porto.alurachallenge.domain.video;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVideo(
    @NotNull
    Long id,
    String titulo,
    String descricao,
    String url
) {

}
