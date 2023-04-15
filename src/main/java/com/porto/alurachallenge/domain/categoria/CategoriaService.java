package com.porto.alurachallenge.domain.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.alurachallenge.domain.video.DadosListagemVideo;
import com.porto.alurachallenge.domain.video.VideoRepository;
import com.porto.alurachallenge.infra.exception.APIException;

import jakarta.validation.Valid;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    public Page<DadosListagemCategoria> listar(Pageable paginacao) {
        return categoriaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCategoria::new);
    }

    public Categoria detalhar(Long id) {
        return categoriaRepository.getReferenceById(id);
    }

    public Categoria cadastrar(@Valid DadosCadastroCategoria dados) {
        return categoriaRepository.save(new Categoria(dados));
    }

    public Categoria atualizar(@Valid DadosAtualizacaoCategoria dados) {
        validarSeCategoriaExiste(dados.id());

        var categoria = categoriaRepository.getReferenceById(dados.id());
        return categoria.atualizarInformacoes(dados);
    }

    public void excluir(Long id) {
        validarSeCategoriaExiste(id);

        var categoria = categoriaRepository.getReferenceById(id);
        categoria.excluir();
    }

    public Page<DadosListagemVideo> listarVideosPorCategoria(Long id, Pageable paginacao) {
        validarSeCategoriaExiste(id);

        return videoRepository.listarTodosOsVideosAtivosPorCategoria(id, paginacao).map(DadosListagemVideo::new);
    }

    private void validarSeCategoriaExiste(Long id){
        if(!categoriaRepository.existsByIdAndAtivoTrue(id)){
            throw new APIException("Categoria informada não existe!");
        }
    }
   
}
