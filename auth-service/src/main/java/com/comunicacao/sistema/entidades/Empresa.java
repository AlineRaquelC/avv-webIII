package com.comunicacao.sistema.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;




import lombok.Data;


@Data 
@Entity
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String cnpj;
	@Column
	private String razaoSocial;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "empresa_id")
    private List<Cliente> clientes = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "empresa_funcionario_id")
	private List<Funcionario> funcionarios = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "empresa_id")
	private List<ServicoMercadoria> servicosMercadorias = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "empresa_id")
	private List<Venda> vendas = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "empresa_id")
	private List<Veiculo> veiculos = new ArrayList<>();
}