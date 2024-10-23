package br.com.boletojuros.adapter.datasource.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boletojuros.adapter.datasource.database.entity.BoletoCalculadoEntity;

@Repository
public interface BoletoCalculadoRepository extends JpaRepository<BoletoCalculadoEntity, Long>{
    
}
