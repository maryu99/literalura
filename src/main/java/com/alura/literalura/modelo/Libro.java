package com.alura.literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Column (name = "nombre_autor")
    private String nombreAutor;

    private String idioma;

    private Integer numeroDeDescargas;

    public Libro(){

    }

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.nombreAutor = autor.getNombreAutor();
        this.idioma = datosLibro.idioma().get(0).toString();
        this.numeroDeDescargas= datosLibro.conteoDeDescargas();
        this.autor=autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = String.join(",", idioma);
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "--------------------  LIBRO -------------------- " + "\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + nombreAutor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + numeroDeDescargas + "\n" +
                "------------------------------------------------ ";
    }
}
