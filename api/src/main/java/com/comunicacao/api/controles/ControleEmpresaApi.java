package com.comunicacao.api.controles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ControleEmpresaApi {

	private final String urlBase = "http://localhost:8080";

	@GetMapping("/api/empresas")
	public ResponseEntity<?> obterEmpresas() {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlBase + "/empresas", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/clientes")
	public ResponseEntity<?> obterClientes(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlBase + "/empresas/" + id + "/clientes", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/funcionarios")
	public ResponseEntity<?> obterFuncionarios(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlBase + "/empresas/" + id + "/funcionarios", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/servicos-mercadorias")
	public ResponseEntity<?> obterServicosMercadorias(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlBase + "/empresas/" + id + "/servicos-mercadorias", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/vendas")
	public ResponseEntity<?> obterVendas(@PathVariable Long id,
			@RequestParam String dataInicio,
			@RequestParam String dataFim) {
		String url = urlBase + "/empresas/" + id + "/vendas?dataInicio=" + dataInicio + "&dataFim=" + dataFim;

		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(url, Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/veiculos")
	public ResponseEntity<?> obterVeiculos(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlBase + "/empresas/" + id + "/veiculos", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}
}