package br.com.boletojuros.adapter.datasource_integration.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.domain.enums.TipoBoleto;

public record BoletoDTO(String codigo, @JsonProperty("data_vencimento") LocalDate dataVencimento, BigDecimal valor, TipoBoleto tipo) {
        
        public Boleto toBoleto() {
            var boleto = new Boleto();
            boleto.setCodigo(codigo);
            boleto.setDataVencimento(dataVencimento);
            boleto.setValor(valor);
            boleto.setTipo(tipo);
            
            return boleto;
        }
}
