package com.inss.calculo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inss.calculo.model.FaixaAliquota;
import com.inss.calculo.repository.FaixaAliquotaRepository;

@Service
public class FaixaAliquotaService {

	@Autowired
	private FaixaAliquotaRepository faixaAliquotaRepository;

	public FaixaAliquota insertAliquota(FaixaAliquota aliquota) {

		return faixaAliquotaRepository.save(aliquota);
	}

	public void deleteAliquota(Long id) {
		faixaAliquotaRepository.deleteById(id);
	}

	public List<FaixaAliquota> findAll() {
		return faixaAliquotaRepository.findAll();
	}

	public FaixaAliquota updateAliquota(FaixaAliquota update, long id) {
		FaixaAliquota aliquota = faixaAliquotaRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(update, aliquota, "id");

		return faixaAliquotaRepository.save(aliquota);

	}

	public FaixaAliquota findById(Long id) {
		return faixaAliquotaRepository.findById(id).orElseThrow();
	}

}
