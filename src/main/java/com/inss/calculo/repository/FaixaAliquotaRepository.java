package com.inss.calculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inss.calculo.model.FaixaAliquota;

@Repository
public interface FaixaAliquotaRepository extends JpaRepository<FaixaAliquota, Long> {

}
