package com.comunicacao.sistema.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;


@Entity
@Data
public class ServicoMercadoria {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column
    private String nome;
    @Column(nullable = true)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private Date dataCadastro;
    @Column(nullable = false)
    private String tipo; 
    
}
