package com.comunicacao.sistema.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunicacao.sistema.entidades.Empresa;
import com.comunicacao.sistema.entidades.Venda;
import java.util.stream.Collectors;
import com.comunicacao.sistema.repositorios.RepositorioEmpresa;
import java.text.SimpleDateFormat;

@RestController
public class ControleEmpresa {
	@Autowired
	private RepositorioEmpresa repositorio;

	@GetMapping("/empresas")
	public ResponseEntity<?> obterEmpresas() {
		List<Empresa> empresas = repositorio.findAll();
		return new ResponseEntity<>(empresas, HttpStatus.OK);
	}

	@GetMapping("/empresas/{id}/clientes")
	public ResponseEntity<?> obterEmpresa(@PathVariable Long id) {
		Optional<Empresa> empresa = repositorio.findById(id);
		if (empresa.isPresent()) {
			return new ResponseEntity<>(empresa.get().getClientes(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/empresas/{id}/funcionarios")
	public ResponseEntity<?> obterFuncionarios(@PathVariable Long id) {
		Optional<Empresa> empresa = repositorio.findById(id);
		if (empresa.isPresent()) {
			return new ResponseEntity<>(empresa.get().getFuncionarios(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/empresas/{id}/servicos-mercadorias")
	public ResponseEntity<?> obterServicosMercadorias(@PathVariable Long id) {
		Optional<Empresa> empresa = repositorio.findById(id);
		if (empresa.isPresent()) {
			return new ResponseEntity<>(empresa.get().getServicosMercadorias(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/empresas/{id}/vendas")
	public ResponseEntity<?> obterVendas(@PathVariable Long id, @RequestParam String dataInicio,
			@RequestParam String dataFim) throws Exception {
		Optional<Empresa> empresa = repositorio.findById(id);
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date inicio = formato.parse(dataInicio);
		Date fim = formato.parse(dataFim);
		if (empresa.isPresent()) {
			List<Venda> vendasFiltradas = empresa.get().getVendas()
					.stream()
					.filter(venda -> !venda.getDataVenda().before(inicio) && !venda.getDataVenda().after(fim))
					.collect(Collectors.toList());

			return new ResponseEntity<>(vendasFiltradas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/empresas/{id}/veiculos")
	public ResponseEntity<?> obterVeiculos(@PathVariable Long id) {
		Optional<Empresa> empresa = repositorio.findById(id);
		if (empresa.isPresent()) {
			return new ResponseEntity<>(empresa.get().getVeiculos(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}