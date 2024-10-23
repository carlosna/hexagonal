package br.com.boletojuros.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.boletojuros.core.domain.enums.TipoBoleto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Boleto {
    
    private String codigo;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private TipoBoleto tipo;
}
