package com.comunicacao.sistema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.comunicacao.sistema.entidades.Cliente;
import com.comunicacao.sistema.entidades.Documento;
import com.comunicacao.sistema.entidades.Empresa;
import com.comunicacao.sistema.entidades.Endereco;
import com.comunicacao.sistema.repositorios.RepositorioEmpresa;
import com.comunicacao.sistema.entidades.Funcionario;
import com.comunicacao.sistema.entidades.ServicoMercadoria;
import com.comunicacao.sistema.entidades.Venda;
import com.comunicacao.sistema.entidades.Veiculo;


import java.text.SimpleDateFormat;

import java.math.BigDecimal;

import java.util.Date;

import com.comunicacao.sistema.entidades.Telefone;


@SpringBootApplication
public class SistemaApplication implements CommandLineRunner {

	@Autowired
	private RepositorioEmpresa repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SistemaApplication.class, args);
	}

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run(String... args) throws Exception {

		if (repositorio.count() > 0) {
			return;
		}
		String[] nomes = {
				"Toyota São Paulo",
				"Volkswagen Curitiba",
				"Autobots Matriz"
		};

		String[] cnpjs = {
				"11.111.111/0001-11",
				"22.222.222/0001-22",
				"33.333.333/0001-33"
		};
		String[] nomesServicos = {
				"Troca de óleo",
				"Revisão preventiva",
				"Filtro de ar"
		};

		String[] descricoesServicos = {
				"Substituição do óleo do motor",
				"Verificação geral do veículo",
				"Peça para reposição do sistema de admissão"
		};

		String[] tiposServicos = {
				"SERVICO",
				"SERVICO",
				"MERCADORIA"
		};

		String[] valoresServicos = {
				"100.00",
				"250.00",
				"80.00"
		};

		String[] marcasVeiculos = {
				"Toyota",
				"Volkswagen",
				"Chevrolet"
		};

		String[] modelosVeiculos = {
				"Corolla",
				"Gol",
				"Camaro"
		};

		String[] placasVeiculos = {
				"ABC1A01",
				"DEF2B02",
				"GHI3C03"
		};

		String[] coresVeiculos = {
				"Prata",
				"Branco",
				"Amarelo"
		};

		int[] anosVeiculos = {
				2020,
				2018,
				2022
		};
		for (int i = 0; i < 3; i++) {
			Empresa empresa = new Empresa();

			empresa.setRazaoSocial(nomes[i]);
			empresa.setCnpj(cnpjs[i]);

			Cliente cliente = new Cliente();
			cliente.setNome("Aline " + (i + 1));
			cliente.setNomeSocial("Aline Raquel " + (i + 1) + " LTDA");
			cliente.setDataNascimento(formato.parse("19/09/1990"));
			cliente.setDataCadastro(new Date());

			Documento documento = new Documento();
			documento.setTipo("CPF");
			documento.setNumero("123.456.789-0" + i);
			cliente.getDocumentos().add(documento);

			Endereco endereco = new Endereco();
			endereco.setCodigoPostal("00000-00" + i);
			endereco.setNumero("100" + i);
			endereco.setRua("Rua " + (i + 1));
			endereco.setCidade("São Paulo");
			endereco.setEstado("SP");
			cliente.setEndereco(endereco);

			Telefone telefone = new Telefone();
			telefone.setDdd("11");
			telefone.setNumero("123456789" + i);
			cliente.getTelefones().add(telefone);

			empresa.getClientes().add(cliente);

			Funcionario funcionario = new Funcionario();
			funcionario.setNome("Gerson " + (i + 1));
			funcionario.setNomeSocial("Gerson Pereira " + (i + 1) + " LTDA");
			funcionario.setPerfil("Gerente " + (i + 1));
			funcionario.setDataNascimento(formato.parse("01/01/1980"));
			funcionario.setDataCadastro(new Date());

			Documento docFuncionario = new Documento();
			docFuncionario.setTipo("CPF");
			docFuncionario.setNumero("987.654.321-0" + i);
			funcionario.getDocumentos().add(docFuncionario);

			Endereco enderecoFuncionario = new Endereco();
			enderecoFuncionario.setCodigoPostal("11111-11" + i);
			enderecoFuncionario.setNumero("200" + i);
			enderecoFuncionario.setRua("Avenida " + (i + 1));
			enderecoFuncionario.setCidade("Curitiba");
			enderecoFuncionario.setEstado("PR");
			funcionario.setEndereco(enderecoFuncionario);

			Telefone telefoneFuncionario = new Telefone();
			telefoneFuncionario.setDdd("41");
			telefoneFuncionario.setNumero("987654321" + i);
			funcionario.getTelefones().add(telefoneFuncionario);
			empresa.getFuncionarios().add(funcionario);

			for (int j = 0; j < nomesServicos.length; j++) {
				ServicoMercadoria servicomercadoria = new ServicoMercadoria();
				servicomercadoria.setNome(nomesServicos[j]);
				servicomercadoria.setDescricao(descricoesServicos[j]);
				servicomercadoria.setValor(new BigDecimal(valoresServicos[j]));
				servicomercadoria.setTipo(tiposServicos[j]);
				servicomercadoria.setDataCadastro(new Date());
				empresa.getServicosMercadorias().add(servicomercadoria);
			}

			Venda venda = new Venda();
			venda.setDataVenda(new Date());
			venda.setValorTotal(new BigDecimal("430.00"));
			venda.getServicosMercadorias().addAll(empresa.getServicosMercadorias());
			empresa.getVendas().add(venda);

			Veiculo veiculo = new Veiculo();
			veiculo.setMarca(marcasVeiculos[i]);
			veiculo.setModelo(modelosVeiculos[i]);
			veiculo.setPlaca(placasVeiculos[i]);
			veiculo.setCor(coresVeiculos[i]);
			veiculo.setAno(anosVeiculos[i]);
			veiculo.setDataAtendimento(new Date());

			empresa.getVeiculos().add(veiculo);

			repositorio.save(empresa);
		}
	}
}