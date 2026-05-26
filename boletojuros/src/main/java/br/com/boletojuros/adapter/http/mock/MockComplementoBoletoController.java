package br.com.boletojuros.adapter.http.mock;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.boletojuros.adapter.datasource.integration.dto.BoletoDTO;
import br.com.boletojuros.core.domain.enums.TipoBoleto;

@RestController
@RequestMapping("/mock/boletos")
public class MockComplementoBoletoController {

    @GetMapping("/{codigo}")
    public ResponseEntity<BoletoDTO> getBoleto(@PathVariable String codigo) {
        BoletoDTO boleto = new BoletoDTO();
        boleto.setCodigo(codigo);
        boleto.setDataVencimento(LocalDate.now().minusDays(10));
        boleto.setValor(BigDecimal.valueOf(100.00));
        boleto.setTipo(TipoBoleto.XPTO);

        return ResponseEntity.ok(boleto);
    }
}
