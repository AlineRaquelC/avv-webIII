package com.comunicacao.api.controles;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comunicacao.api.modelos.Login;
import com.comunicacao.api.modelos.Usuario;
import com.comunicacao.api.repositorios.RepositorioUsuario;
import com.comunicacao.api.seguranca.JwtUtil;

@RestController
public class ControleAutenticacao {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		Optional<Usuario> usuario = repositorioUsuario.findByLogin(login.getLogin());

		if (usuario.isPresent() && usuario.get().getSenha().equals(login.getSenha())) {
			String token = jwtUtil.gerarToken(usuario.get().getLogin(), usuario.get().getPerfil());

			Map<String, String> resposta = new HashMap<>();
			resposta.put("token", token);

			return new ResponseEntity<>(resposta, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}