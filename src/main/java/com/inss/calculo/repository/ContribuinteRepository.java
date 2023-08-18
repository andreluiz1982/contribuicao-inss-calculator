package com.inss.calculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inss.calculo.model.Contribuinte;
@Repository
public interface ContribuinteRepository extends JpaRepository<Contribuinte, Long> {

}
