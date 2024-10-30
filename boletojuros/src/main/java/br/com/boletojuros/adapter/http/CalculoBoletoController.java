package br.com.boletojuros.adapter.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.boletojuros.adapter.http.dto.CalculoBoletoRequest;
import br.com.boletojuros.adapter.http.dto.CalculoBoletoResponse;
import br.com.boletojuros.adapter.http.mapper.CalculoBoletoMapper;
import br.com.boletojuros.core.port.input.CalculoBoletoPort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/boleto")
public class CalculoBoletoController {
    
    private final CalculoBoletoPort calculoBoletoPort;
    private final CalculoBoletoMapper calculoBoletoMapper;

    public CalculoBoletoController(CalculoBoletoPort calculoBoletoPort, CalculoBoletoMapper calculoBoletoMapper) {
        this.calculoBoletoPort = calculoBoletoPort;
        this.calculoBoletoMapper = calculoBoletoMapper;
    }

    @PostMapping("/calculo")
    @Operation(summary = "Calcular o juros de um boleto")
    public ResponseEntity<CalculoBoletoResponse> calcularBoleto(@Valid @RequestBody CalculoBoletoRequest request) {
        var boletoCalculado = calculoBoletoPort.executar(request.getCodigo(), request.getDataPagamento()); 

        return ResponseEntity.ok(calculoBoletoMapper.toDTO(boletoCalculado));
    }
}
