package br.com.boletojuros.adapter.datasource_integration;

import br.com.boletojuros.adapter.datasource_integration.client.ComplementoBoletoClient;
import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.port.output.ComplementoBoletoPort;

public class ComplementoBoletoIntegration implements ComplementoBoletoPort {
    
    private final ComplementoBoletoClient client;

    public ComplementoBoletoIntegration(br.com.boletojuros.adapter.datasource_integration.client.ComplementoBoletoClient client) {
        this.client = client;
    }
    

    @Override
    public Boleto executar(String codigo) {
        var boletoDTO = client.getBoleto(codigo);
        Boleto boleto = boletoDTO.toBoleto();

        return boleto;
    }
    
}
