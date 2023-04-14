package com.porto.alurachallenge.domain.video;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroVideo(
    @NotBlank
    String titulo,
    @NotBlank
    String descricao,
    @NotBlank
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    String url
) {

}
