package com.inss.calculo.service;

import java.util.List;

import com.inss.calculo.service.exceptions.DuplicatedFieldException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.repository.SalarioBaseContribuicaoRepository;

@Service
public class SalarioContribuicaoService {

	@Autowired
	private SalarioBaseContribuicaoRepository contribuicaoRepository;

	public SalarioBaseContribuicao insertSalarioBaseContribuicao(SalarioBaseContribuicao obj) {
		try {
			return contribuicaoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicatedFieldException(String.format("Salário de contribuição para o mês %s já existe, " +
							"faça uma atualização ou exclua.",
					obj.getAnoMes().toString()));
		}
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

	public List<SalarioBaseContribuicao> getContribuicoesPerContribuinte(Long id) {
		List<SalarioBaseContribuicao> list = contribuicaoRepository.findByContribuinteId(id);
		return list;
	}
}
