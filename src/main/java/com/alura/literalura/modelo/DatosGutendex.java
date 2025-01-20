package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGutendex(
        @JsonAlias("count") Integer conteo,

        @JsonAlias("next") String paginaSiguiente,

        @JsonAlias("previous") String paginaAnterior,

        @JsonAlias("results") List<DatosLibro> libros
) {
}
