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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.porto.alurachallenge.domain.categoria.CategoriaService;
import com.porto.alurachallenge.domain.categoria.DadosAtualizacaoCategoria;
import com.porto.alurachallenge.domain.categoria.DadosCadastroCategoria;
import com.porto.alurachallenge.domain.categoria.DadosDetalhamentoCategoria;
import com.porto.alurachallenge.domain.categoria.DadosListagemCategoria;
import com.porto.alurachallenge.domain.video.DadosListagemVideo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemCategoria>> listar(Pageable paginacao){
        var categorias = categoriaService.listar(paginacao);

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable Long id){
        var categoria = categoriaService.detalhar(id);

        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder){
        var categoria = categoriaService.cadastrar(dados);

        var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados){
        var categoria = categoriaService.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        categoriaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<DadosListagemVideo>> listarVideosPorCategoria(@PathVariable Long id, Pageable paginacao){
        var videos = categoriaService.listarVideosPorCategoria(id, paginacao);

        return ResponseEntity.ok(videos);
    }

}

