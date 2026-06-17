package com.comunicacao.api.controles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.comunicacao.api.modelos.Login;
import com.comunicacao.api.seguranca.JwtUtil;

@RestController
public class ControleAutenticacao {

	private JwtUtil jwtUtil;

	public ControleAutenticacao(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		try {
			ResponseEntity<Map> respostaUsuario = new RestTemplate()
					.postForEntity("http://localhost:8080/usuarios/login", login, Map.class);

			Map<String, String> usuario = respostaUsuario.getBody();

			String token = jwtUtil.gerarToken(usuario.get("login"), usuario.get("perfil"));

			Map<String, String> resposta = new HashMap<>();
			resposta.put("token", token);

			return new ResponseEntity<>(resposta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}