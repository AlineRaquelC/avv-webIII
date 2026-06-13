package com.comunicacao.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.comunicacao.api.modelos.Usuario;
import com.comunicacao.api.repositorios.RepositorioUsuario;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (repositorioUsuario.count() > 0) {
			return;
		}

		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha("123");
		usuario.setPerfil("ADMIN");

		repositorioUsuario.save(usuario);
	}
}
