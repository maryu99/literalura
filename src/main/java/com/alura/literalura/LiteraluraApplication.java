package com.alura.literalura;

import com.alura.literalura.principal.Principal;
import com.alura.literalura.repository.IAutorRepository;
import com.alura.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
 //de la interface repository
    @Autowired
 	private IAutorRepository autorRepositorio;
	@Autowired
	private ILibroRepository libroRepositorio;
	//
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal ejecucionPrograma = new Principal(autorRepositorio, libroRepositorio);
		ejecucionPrograma.muestraMenu();
	}
}
