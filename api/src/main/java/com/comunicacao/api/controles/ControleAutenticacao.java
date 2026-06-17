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
import org.springframework.beans.factory.annotation.Value;

@RestController
public class ControleAutenticacao {

	private JwtUtil jwtUtil;
	@Value("${auth.service.url:http://localhost:8081}")
    private String authServiceUrl;

	public ControleAutenticacao(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		try {
			ResponseEntity<?> respostaUsuario = new RestTemplate()
					.postForEntity(authServiceUrl + "/usuarios/login", login, Map.class);

			Map<String, String> usuario = (Map<String, String>) respostaUsuario.getBody();

			String token = jwtUtil.gerarToken(usuario.get("login"), usuario.get("perfil"));

			Map<String, String> resposta = new HashMap<>();
			resposta.put("token", token);

			return new ResponseEntity<>(resposta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}