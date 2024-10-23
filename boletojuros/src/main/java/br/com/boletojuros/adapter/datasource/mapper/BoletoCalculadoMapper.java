package br.com.boletojuros.adapter.datasource.mapper;

import org.mapstruct.Mapper;

import br.com.boletojuros.adapter.datasource.database.entity.BoletoCalculadoEntity;
import br.com.boletojuros.core.domain.BoletoCalculado;

@Mapper(componentModel = "spring")
public interface BoletoCalculadoMapper{

    BoletoCalculadoEntity toEntity(BoletoCalculado boletoCalculado);
}
