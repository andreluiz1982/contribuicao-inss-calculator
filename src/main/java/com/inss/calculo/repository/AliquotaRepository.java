package com.inss.calculo.repository;

import java.time.YearMonth;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inss.calculo.model.Aliquota;
@Repository
public interface AliquotaRepository extends JpaRepository<Aliquota, Long> {

	Optional<Aliquota> findByAnoMesBefore(YearMonth anoMes);
}
