package com.porto.alurachallenge.domain.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select c.ativo
            from Categoria c
            where
            c.id = :id
            """)
    Boolean findAtivoById(Long id);

    boolean existsByIdAndAtivoTrue(Long id);

}
