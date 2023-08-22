package com.inss.calculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inss.calculo.model.SalarioBaseContribuicao;
@Repository
public interface SalarioBaseContribuicaoRepository extends JpaRepository<SalarioBaseContribuicao, Long> {

}
