package br.com.boletojuros.adapter.datasource.database.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.boletojuros.core.domain.enums.TipoBoleto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boleto_calculado")
@Getter
@Setter
@Builder
public class BoletoCalculadoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "valor_original")
    private BigDecimal valorOriginal;

    @Column(name = "valor")
    private BigDecimal valor;
    
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Column(name = "juros")
    private BigDecimal juros;

    @Column(name = "tipo")
    private TipoBoleto tipo;

}
