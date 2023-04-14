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

import com.porto.alurachallenge.domain.categoria.Categoria;
import com.porto.alurachallenge.domain.categoria.CategoriaRepository;
import com.porto.alurachallenge.domain.categoria.DadosAtualizacaoCategoria;
import com.porto.alurachallenge.domain.categoria.DadosCadastroCategoria;
import com.porto.alurachallenge.domain.categoria.DadosDetalhamentoCategoria;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        var categorias = categoriaRepository.findAllByAtivoTrue();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable Long id){
        var categoria = categoriaRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder){
        var categoria = new Categoria(dados);
        categoriaRepository.save(categoria);

        var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@RequestBody @Valid DadosAtualizacaoCategoria dados){
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.excluir();

        return ResponseEntity.noContent().build();
    }

}

