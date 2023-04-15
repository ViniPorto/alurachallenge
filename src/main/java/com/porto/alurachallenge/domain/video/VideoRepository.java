package com.porto.alurachallenge.domain.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Page<Video> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select v
            from Video v
            where 
            v.ativo = 1
            and 
            v.categoria = (select c from Categoria c where c.id = :id)
            """)
    Page<Video> listarTodosOsVideosAtivosPorCategoria(Long id, Pageable paginacao);

    @Query("""
            select v
            from Video v
            where
            v.ativo = 1
            and v.titulo like %:titulo%
            """)
    Page<Video> listarTodosOsVideoAtivosPorTitulo(String titulo, Pageable paginacao);
    
}
