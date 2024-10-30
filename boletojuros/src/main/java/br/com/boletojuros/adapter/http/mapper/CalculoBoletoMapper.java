package br.com.boletojuros.adapter.http.mapper;

import org.mapstruct.Mapper;

import br.com.boletojuros.adapter.http.dto.CalculoBoletoResponse;
import br.com.boletojuros.core.domain.BoletoCalculado;

@Mapper(componentModel = "spring")
public interface CalculoBoletoMapper {
    
    CalculoBoletoResponse toDTO(BoletoCalculado boletoCalculado);
}
