package br.com.boletojuros.core.port.output;

import br.com.boletojuros.core.domain.Boleto;

public interface ComplementoBoletoPort {
    Boleto executar(String codigo);
}
