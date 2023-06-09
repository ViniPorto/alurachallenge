package com.porto.alurachallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.porto.alurachallenge.domain.video.DadosAtualizacaoVideo;
import com.porto.alurachallenge.domain.video.DadosCadastroVideo;
import com.porto.alurachallenge.domain.video.DadosDetalhamentoVideo;
import com.porto.alurachallenge.domain.video.DadosListagemVideo;
import com.porto.alurachallenge.domain.video.VideoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoVideo> cadastrar(@RequestBody @Valid DadosCadastroVideo dados, UriComponentsBuilder uriBuilder){
        var video = videoService.cadastrar(dados);

        var uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoVideo(video));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoVideo> atualizar(@RequestBody @Valid DadosAtualizacaoVideo dados){
        var video = videoService.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemVideo>> listar(Pageable paginacao, @RequestParam(value = "titulo", required = false) String titulo){
        if(titulo == null){
            return ResponseEntity.ok(videoService.listar(paginacao));
        }
        return ResponseEntity.ok(videoService.listarPorTitulo(titulo, paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoVideo> detalhar(@PathVariable Long id){
        var video = videoService.detalhar(id);

        return ResponseEntity.ok().body(new DadosDetalhamentoVideo(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        videoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
