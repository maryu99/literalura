package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,

        @JsonAlias("authors") List<DatosAutor> datosAutor,

        @JsonAlias("languages") List <String> idioma,

        @JsonAlias("download_count") Integer conteoDeDescargas
) {
    @Override
    public String toString() {
        return " Titulo = " + titulo +
                "| Datos del Autor = " + datosAutor +
                "| Idioma= " + idioma +
                "| Descargas del libro = " + conteoDeDescargas;
    }
}
