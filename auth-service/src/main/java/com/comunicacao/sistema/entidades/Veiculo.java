package com.comunicacao.sistema.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import lombok.Data;

@Entity
@Data
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false, unique = true)
    private String placa;
    @Column(nullable = false)
    private int ano;
    @Column
    private String cor;
    @Column(nullable = false)
    private Date dataAtendimento;
}
