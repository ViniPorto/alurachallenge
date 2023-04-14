package com.porto.alurachallenge.domain.video;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "video")
@Entity(name = "Video")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Video {
    
    public Video(@Valid DadosCadastroVideo dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private boolean ativo;

    public void atualizarInformacoes(@Valid DadosAtualizacaoVideo dados) {
        if(dados.titulo() != null) this.titulo = dados.titulo();
        if(dados.descricao() != null) this.descricao = dados.descricao();
        if(dados.url() != null) this.url = dados.url();
    }

    public void excluir() {
        this.ativo = false;
    }
    
}
