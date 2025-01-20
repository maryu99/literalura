package com.alura.literalura.repository;

import com.alura.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombreAutorContainsIgnoreCase(String nombreAutor);

    List<Autor> findByAnioNacimientoGreaterThanEqualAndAnioFallecimientoLessThanEqual(int anioNacimiento, int anioFallecimiento);
}
