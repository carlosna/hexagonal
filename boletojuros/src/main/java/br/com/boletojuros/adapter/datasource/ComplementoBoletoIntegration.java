package br.com.boletojuros.adapter.datasource;

import br.com.boletojuros.adapter.datasource.integration.client.ComplementoBoletoClient;
import br.com.boletojuros.adapter.datasource.integration.dto.BoletoDTO;
import br.com.boletojuros.adapter.datasource.mapper.BoletoMapper;
import br.com.boletojuros.core.domain.Boleto;
import br.com.boletojuros.core.port.output.ComplementoBoletoPort;

public class ComplementoBoletoIntegration implements ComplementoBoletoPort {
    
    private final ComplementoBoletoClient client;
    private final BoletoMapper boletoMapper;

    public ComplementoBoletoIntegration(br.com.boletojuros.adapter.datasource.integration.client.ComplementoBoletoClient client, BoletoMapper boletoMapper) {
        this.boletoMapper = boletoMapper;
        this.client = client;
    }
    

    @Override
    public Boleto executar(String codigo) {
        BoletoDTO boletoDTO = client.getBoleto(codigo);

        return boletoMapper.toDomain(boletoDTO);
    }
    
}
