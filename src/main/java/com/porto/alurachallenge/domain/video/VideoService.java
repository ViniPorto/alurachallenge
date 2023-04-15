package com.porto.alurachallenge.domain.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.alurachallenge.domain.categoria.CategoriaRepository;
import com.porto.alurachallenge.infra.exception.APIException;

import jakarta.validation.Valid;

@Service
public class VideoService {
    
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private List<ValidadorCadastroDeVideo> validadoresCadastro;

    @Autowired
    private List<ValidadorAtualizacaoDeVideo> validadoresAtualizacao;

    public Video cadastrar(@Valid DadosCadastroVideo dados) {
        var categoriaId = dados.categoriaId();
        if(categoriaId == null){
            categoriaId = 1l; 
        }
        validarSeCategoriaExiste(dados.categoriaId());

        var categoria = categoriaRepository.getReferenceById(categoriaId);

        validadoresCadastro.forEach(v -> v.validar(dados));

        return videoRepository.save(new Video(dados, categoria));
    }

    public Video atualizar(@Valid DadosAtualizacaoVideo dados) {
        var categoriaId = dados.categoriaId();
        if(categoriaId == null){
            categoriaId = 1l; 
        }
        validarSeCategoriaExiste(dados.categoriaId());
        
        var categoria = categoriaRepository.getReferenceById(categoriaId);

        validadoresAtualizacao.forEach(v -> v.validar(dados));

        var video = videoRepository.getReferenceById(dados.id());
        return video.atualizarInformacoes(dados, categoria);
    }

    public Page<DadosListagemVideo> listar(Pageable paginacao) {
        return videoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemVideo::new);
    }

    public Video detalhar(Long id) {
        return videoRepository.getReferenceById(id);
    }

    public void excluir(Long id) {
        var video = videoRepository.getReferenceById(id);
        video.excluir();
    }

    public Page<DadosListagemVideo> listarPorTitulo(String titulo, Pageable paginacao) {
        return videoRepository.listarTodosOsVideoAtivosPorTitulo(titulo, paginacao).map(DadosListagemVideo::new);
    }

    private void validarSeCategoriaExiste(Long id){
        if(!categoriaRepository.existsByIdAndAtivoTrue(id)){
            throw new APIException("Categoria informada não existe!");
        }
    }
}
