package com.porto.alurachallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.porto.alurachallenge.domain.video.DadosAtualizacaoVideo;
import com.porto.alurachallenge.domain.video.DadosCadastroVideo;
import com.porto.alurachallenge.domain.video.DadosDetalhamentoVideo;
import com.porto.alurachallenge.domain.video.Video;
import com.porto.alurachallenge.domain.video.VideoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoVideo> cadastrar(@RequestBody @Valid DadosCadastroVideo dados, UriComponentsBuilder uriBuilder){
        var video = new Video(dados);
        repository.save(video);

        var uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVideo(video));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoVideo> atualizar(@RequestBody @Valid DadosAtualizacaoVideo dados){
        var video = repository.getReferenceById(dados.id());
        video.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @GetMapping
    public ResponseEntity<List<Video>> listar(){
        var videos = repository.findAllByAtivoTrue();

        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoVideo> detalhar(@PathVariable Long id){
        var video = repository.getReferenceById(id);

        return ResponseEntity.ok().body(new DadosDetalhamentoVideo(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        var video = repository.getReferenceById(id);
        video.excluir();

        return ResponseEntity.noContent().build();
    }

}
