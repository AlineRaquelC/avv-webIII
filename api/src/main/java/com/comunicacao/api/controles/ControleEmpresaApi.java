package com.comunicacao.api.controles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class ControleEmpresaApi {

	@Value("${clientes.service.url:http://localhost:8082}")
	private String urlClientes;

	@Value("${funcionarios.service.url:http://localhost:8083}")
	private String urlFuncionarios;

	@Value("${servicos.service.url:http://localhost:8084}")
	private String urlServicos;

	@Value("${veiculos.service.url:http://localhost:8085}")
	private String urlVeiculos;

	@Value("${vendas.service.url:http://localhost:8086}")
	private String urlVendas;

	@GetMapping("/api/empresas")
	public ResponseEntity<?> obterEmpresas() {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlClientes + "/empresas", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/clientes")
	public ResponseEntity<?> obterClientes(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlClientes + "/empresas/" + id + "/clientes", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/funcionarios")
	public ResponseEntity<?> obterFuncionarios(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlFuncionarios + "/empresas/" + id + "/funcionarios", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/servicos-mercadorias")
	public ResponseEntity<?> obterServicosMercadorias(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlServicos + "/empresas/" + id + "/servicos-mercadorias", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/vendas")
	public ResponseEntity<?> obterVendas(@PathVariable Long id,
			@RequestParam String dataInicio,
			@RequestParam String dataFim) {
		String url = urlVendas + "/empresas/" + id + "/vendas?dataInicio=" + dataInicio + "&dataFim=" + dataFim;

		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(url, Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}

	@GetMapping("/api/empresas/{id}/veiculos")
	public ResponseEntity<?> obterVeiculos(@PathVariable Long id) {
		ResponseEntity<Object> resposta = new RestTemplate()
				.getForEntity(urlVeiculos + "/empresas/" + id + "/veiculos", Object.class);

		return new ResponseEntity<>(resposta.getBody(), HttpStatus.OK);
	}
}