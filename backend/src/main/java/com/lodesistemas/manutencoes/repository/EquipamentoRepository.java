package com.lodesistemas.manutencoes.repository;

import com.lodesistemas.manutencoes.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}