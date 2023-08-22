package com.inss.calculo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.repository.SalarioBaseContribuicaoRepository;

@Service
public class SalarioContribuicaoService {

	@Autowired
	private SalarioBaseContribuicaoRepository contribuicaoRepository;

	public SalarioBaseContribuicao insertSalarioBaseContribuicao(SalarioBaseContribuicao obj) {

		return contribuicaoRepository.save(obj);
	}

	public void deleteSalarioBaseContribuicao(Long id) {
		contribuicaoRepository.deleteById(id);
	}

	public SalarioBaseContribuicao findById(Long id) {
		return contribuicaoRepository.findById(id).orElseThrow();
	}

	public List<SalarioBaseContribuicao> findAll() {
		return contribuicaoRepository.findAll();
	}

	public SalarioBaseContribuicao updateSalarioBaseContribuicao(SalarioBaseContribuicao update, long id) {
		SalarioBaseContribuicao obj = contribuicaoRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(update, obj, "id");

		return contribuicaoRepository.save(obj);

	}

}
