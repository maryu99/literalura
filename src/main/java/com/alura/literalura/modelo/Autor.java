package com.alura.literalura.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreAutor;

    private Integer anioNacimiento;

    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> librosDelAutor = new ArrayList<>();

    public Autor(){ }

    public Autor(DatosAutor datosAutor) {
        this.nombreAutor = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioFallecimiento = datosAutor.anioFallecimiento();
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibrosDelAutor() {
        return librosDelAutor;
    }

    public void setLibrosDelAutor(List<Libro> librosDelAutor) {
        this.librosDelAutor = librosDelAutor;
    }

    @Override
    public String toString() {
        StringBuilder librosTitulos = new StringBuilder();
        for (Libro libro : librosDelAutor) {
            librosTitulos.append(libro.getTitulo()).append(", ");
        }

        var nombreLibroOrdenado = nombreAutor;
        String[] partes = nombreAutor.split(",\\s*");
        if (partes.length == 2) {
            nombreLibroOrdenado = partes[1] + " " + partes[0]; // Orden: Nombre Apellido
        }

        return  "--------------- AUTOR ---------------" + "\n" +
                "Autor: " + nombreLibroOrdenado + "\n" +
                "Año de nacimiento: " + anioNacimiento + "\n" +
                "Año de fallecimiento: " + anioFallecimiento + "\n" +
                "Libros: " + librosTitulos + "\n" +
                "--------------------------------------";
    }
}
