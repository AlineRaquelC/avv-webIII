package com.comunicacao.sistema.entidades;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.List;
import javax.persistence.CascadeType;


import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date dataVenda;
    @Column(nullable = false)
    private BigDecimal valorTotal;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
    private List<ServicoMercadoria> servicosMercadorias = new ArrayList<>();
}
