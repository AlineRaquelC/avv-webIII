package com.comunicacao.api.controles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControleEasterEgg {

	@GetMapping("/easter-egg")
	public ResponseEntity<?> easterEgg() {
		Map<String, String> resposta = new HashMap<>();

		resposta.put("titulo", "GG");
		resposta.put("mensagem", "A P.A ja garantiu a vantagem no mapa. Agora e so destruir o Ancient.");
		resposta.put("combo", "Paysandu + Dota + API REST = GG WP");
		resposta.put("status", "Token validado com sucesso");

		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
}