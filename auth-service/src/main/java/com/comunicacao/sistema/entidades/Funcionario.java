package com.comunicacao.sistema.entidades;

import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Funcionario {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
    @Column
    private String nomeSocial;
	@Column
	private String perfil;
	@Column
	private Date dataNascimento;
    @Column
    private Date dataCadastro;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "funcionario_documento_id")
	private List<Documento> documentos = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "funcionario_endereco_id")
	private Endereco endereco;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "funcionario_telefone_id")
	private List<Telefone> telefones = new ArrayList<>();
}
