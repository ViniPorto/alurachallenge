package com.porto.alurachallenge.domain.categoria;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(
    @NotNull
    Long id,
    String titulo,
    @Enumerated
    Cor cor
) {

}
