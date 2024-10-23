package br.com.boletojuros.core.port.output;

import br.com.boletojuros.core.domain.BoletoCalculado;

public interface SalvarCalculoBoletoPort {
    void executar(BoletoCalculado boletoCalculado);
}