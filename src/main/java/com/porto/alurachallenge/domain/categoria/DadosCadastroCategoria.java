package com.porto.alurachallenge.domain.categoria;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCategoria(
    @NotBlank
    String titulo,
    @NotNull
    @Enumerated
    Cor cor
) {
    
}
