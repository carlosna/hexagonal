package br.com.boletojuros.core.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.domain.BoletoCalculado;
import br.com.boletojuros.core.domain.enums.TipoBoleto;
import br.com.boletojuros.core.domain.enums.TipoException;
import br.com.boletojuros.core.exceptions.ApplicationException;
import br.com.boletojuros.core.port.input.CalculoBoletoPort;
import br.com.boletojuros.core.port.output.ComplementoBoletoPort;
import br.com.boletojuros.core.port.output.SalvarCalculoBoletoPort;

@Service
public class CalcularBoletoUseCase implements CalculoBoletoPort {
    
    private final ComplementoBoletoPort complementoBoletoPort;
    private final SalvarCalculoBoletoPort salvarCalculoBoletoPort;
    
    private static final BigDecimal JUROS_DIARIO = BigDecimal.valueOf(0.033);

    public CalcularBoletoUseCase(
                                 br.com.boletojuros.core.port.output.ComplementoBoletoPort complementoBoletoPort, 
                                 br.com.boletojuros.core.port.output.SalvarCalculoBoletoPort salvarCalculoBoletoPort ) {
        this.complementoBoletoPort = complementoBoletoPort;
        this.salvarCalculoBoletoPort = salvarCalculoBoletoPort;
    }


    @Override
    public BoletoCalculado executar(String codigo, LocalDate dataPagamento) {
        var boleto = complementoBoletoPort.executar(codigo);
        validar(boleto);
        var diasVencidos = getDiasVencido(boleto.getDataVencimento(), dataPagamento);
        var valorJurosDiario = JUROS_DIARIO.multiply(boleto.getValor()).divide(BigDecimal.valueOf(100));
        var juros = valorJurosDiario.multiply(BigDecimal.valueOf(diasVencidos)).setScale(2, RoundingMode.HALF_EVEN);
        var boletoCalculado = BoletoCalculado.builder()
                                                .codigo(boleto.getCodigo())
                                                .dataPagamento(dataPagamento)
                                                .dataVencimento(boleto.getDataVencimento())
                                                .juros(juros)
                                                .tipo(boleto.getTipo())
                                                .valorOriginal(boleto.getValor())
                                                .valor(juros.add(boleto.getValor()))
                                                .build();

        salvarCalculoBoletoPort.executar(boletoCalculado);

        return boletoCalculado;

    }

    private void validar(Boleto  boleto) {

        if(boleto == null) throw new ApplicationException(TipoException.BOLETO_INVALIDO);

        if(boleto.getTipo() !=  TipoBoleto.XPTO) throw new ApplicationException(TipoException.TIPO_BOLETO_INVALIDO);
        
        if (boleto.getDataVencimento().isAfter(LocalDate.now())) throw new ApplicationException(TipoException.BOLETO_NAO_VENCIDO);
        
    }

    private Long getDiasVencido(LocalDate dataVencimento, LocalDate dataPagamento) {
        return ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
    }
    
}
