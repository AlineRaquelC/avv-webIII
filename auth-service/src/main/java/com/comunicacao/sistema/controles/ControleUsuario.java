package com.comunicacao.sistema.controles;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comunicacao.sistema.entidades.Usuario;
import com.comunicacao.sistema.modelos.Login;
import com.comunicacao.sistema.repositorios.RepositorioUsuario;

@RestController
public class ControleUsuario {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@PostMapping("/usuarios/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		Optional<Usuario> usuario = repositorioUsuario.findByLogin(login.getLogin());

		if (usuario.isPresent() && usuario.get().getSenha().equals(login.getSenha())) {
			Map<String, String> resposta = new HashMap<>();
			resposta.put("login", usuario.get().getLogin());
			resposta.put("perfil", usuario.get().getPerfil());

			return new ResponseEntity<>(resposta, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
