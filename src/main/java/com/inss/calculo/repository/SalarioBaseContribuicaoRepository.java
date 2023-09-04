package com.inss.calculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inss.calculo.model.SalarioBaseContribuicao;

import java.util.List;

@Repository
public interface SalarioBaseContribuicaoRepository extends JpaRepository<SalarioBaseContribuicao, Long> {

    List<SalarioBaseContribuicao> findByContribuinteId(Long id);
}
