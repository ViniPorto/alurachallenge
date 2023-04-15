package com.porto.alurachallenge.domain.categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "categoria")
@Entity(name = "Categoria")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {
    
    public Categoria(@Valid DadosCadastroCategoria dados) {
        this.titulo = dados.titulo();
        this.cor = dados.cor();
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Cor cor;
    private boolean ativo;

    public Categoria atualizarInformacoes(@Valid DadosAtualizacaoCategoria dados) {
        if(dados.titulo() != null) this.titulo = dados.titulo();
        if(dados.cor() != null) this.cor = dados.cor();
        return this;
    }

    public void excluir() {
        this.ativo = false;
    }

}
