package com.alura.literalura.principal;

import com.alura.literalura.modelo.*;
import com.alura.literalura.repository.IAutorRepository;
import com.alura.literalura.repository.ILibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoApi = new ConsumoAPI();

    private ConvierteDatos conversor = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books/";

    private Libro libro = null;

    private IAutorRepository autorRepositorio;

    private ILibroRepository libroRepositorio;

    private List<Libro> libros;

    private List<Autor> autores;

    public Principal(IAutorRepository autorRepositorio, ILibroRepository libroRepositorio) {
        this.autorRepositorio = autorRepositorio;
        this.libroRepositorio = libroRepositorio;
    }

    private String menuPrincipal = """
            ##################################################
            |          ELIGE UNA OPCIÓN DEL MENÚ             |
            |________________________________________________|
            _____________________ Menu _______________________
            1 - Registrar libro
            2 - Consultar libros registrados
            3 - Consultar autores registrados
            4 - Consultar autores vivos en un determinado año
            5 - Consultar Libros por idioma
            0 - Salir
            ##################################################
            """;

    private String mensajeConfirmacionRegistro = """
            Confirmación: Desea registrar el libro? 
            1 - Si
            0 - No
            """;

    private String menuIdiomas = """
            es = español
            en = inglés
            fr = francés
            pt - portugués
            it = italiano
            """;

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0) {
            System.out.println(menuPrincipal);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarLibrosRegistrados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivosEnXAnio();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Libro registrarLibroEnBD(DatosLibro datosLibro, Autor autor){
        if(autor != null){
            return new Libro(datosLibro, autor);
        }
        else {
            System.out.println("<Formato Inválido> El campo 'autor' se encuentra vacío, no se puede crear el Libro ");
            return null;
        }
    }
    private void buscarLibro(){
        System.out.println("Escribe el nombre del libro que deseas registrar: ");
        var libroBuscado = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + libroBuscado.replace(" ", "+"));
        DatosGutendex datosLibroGutendex = conversor.obtenerDatos(json, DatosGutendex.class);

        Optional<DatosLibro> libroPorRegistrar = datosLibroGutendex.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(libroBuscado.toUpperCase()))
                .findFirst();

        if (libroPorRegistrar.isPresent()){
            //Se retorna el libro encontrado con la API Gutendex
            System.out.println("Los datos del libro encontrado son: " + libroPorRegistrar.get());

            //Se solicita confirmación para registrar libro en BD
            System.out.println(mensajeConfirmacionRegistro);
            var confirmacionRegistro = teclado.nextInt();
            if (confirmacionRegistro == 1){
                DatosLibro datosLibro = libroPorRegistrar.get();
                DatosAutor datosAutor = datosLibro.datosAutor().get(0);

                Optional<Libro> libroComprobacionExistencia = libroRepositorio.findByTituloContainsIgnoreCase(datosLibro.titulo());
                if (libroComprobacionExistencia.isPresent()){
                    System.out.println(" <Libro Existente> El libro ya se encuentra en la Base de Datos!");
                    System.out.println(libroComprobacionExistencia);
                } else {
                    Autor autorComprobacionExistencia = autorRepositorio.findByNombreAutorContainsIgnoreCase(datosAutor.nombre());
                    if (autorComprobacionExistencia != null){
                        //Acciones de registro del libro con Autor ya Existente
                        libro = registrarLibroEnBD(datosLibro, autorComprobacionExistencia);
                        libroRepositorio.save(libro);
                        System.out.println("---------- LIBRO REGISTRADO ----------");
                        System.out.println(libro);
                    }
                    else {
                        //Acción de registro desde 0
                        Autor autor = new Autor(datosAutor);
                        autor = autorRepositorio.save(autor);
                        libro = registrarLibroEnBD(datosLibro, autor);
                        libroRepositorio.save(libro);
                        System.out.println("---------- LIBRO REGISTRADO ----------");
                        System.out.println(libro);
                    }

                }

            }
            else {
                System.out.println("<Registro Cancelado>");
            }
        }
        else {
            System.out.println("\n <Libro no encontrado>  Verifica bien el titulo o intente con algún libro diferente\n");
        }
    }

    private void buscarLibrosRegistrados(){
        libros = libroRepositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void buscarAutoresRegistrados(){
        autores = autorRepositorio.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void buscarAutoresVivosEnXAnio(){
        System.out.println("Ingrese el año de base");
        var anioBase = teclado.nextInt();
        System.out.println("Ingrese el año límite de búsqueda");
        var anioLimite = teclado.nextInt();
        autores = autorRepositorio.findByAnioNacimientoGreaterThanEqualAndAnioFallecimientoLessThanEqual(anioBase, anioLimite);
        autores.stream()
                .forEach(System.out::println);
    }

    private void buscarLibrosPorIdioma(){
        System.out.println("Selecciona el prefijo del idioma deseado:");
        System.out.println(menuIdiomas);
        var idiomaSeleccionado = teclado.nextLine();
        libros = libroRepositorio.librosPorIdioma(idiomaSeleccionado);
        libros.forEach(System.out::println);
    }
}