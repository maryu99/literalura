package com.alura.literalura.repository;

import com.alura.literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    @Query(" SELECT l FROM Libro l WHERE l.idioma = :idioma" )
    List<Libro> librosPorIdioma(String idioma);


    //List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();
}

