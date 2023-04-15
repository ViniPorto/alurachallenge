package com.porto.alurachallenge.domain.video;

import com.porto.alurachallenge.domain.categoria.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    public Video(@Valid DadosCadastroVideo dados, Categoria categoria) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
        this.ativo = true;
        this.categoria = categoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private boolean ativo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Video atualizarInformacoes(@Valid DadosAtualizacaoVideo dados, Categoria categoria) {
        if(dados.titulo() != null) this.titulo = dados.titulo();
        if(dados.descricao() != null) this.descricao = dados.descricao();
        if(dados.url() != null) this.url = dados.url();
        if(categoria != null) this.categoria = categoria;
        return this;
    }

    public void excluir() {
        this.ativo = false;
    }
    
}
