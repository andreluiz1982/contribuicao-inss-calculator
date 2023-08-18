package com.inss.calculo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inss.calculo.model.Contribuinte;
import com.inss.calculo.repository.ContribuinteRepository;

@Service
public class ContribuinteService {

	@Autowired
	private ContribuinteRepository contribuinteRepository;

	public Contribuinte insertContribuinte(Contribuinte contribuinte) {

		return contribuinteRepository.save(contribuinte);
	}

	public void deleteContribuinte(Long id) {
		contribuinteRepository.deleteById(id);
	}

	public List<Contribuinte> findAll() {
		return contribuinteRepository.findAll();
	}

	public Contribuinte findById(Long id) {
		return contribuinteRepository.findById(id).orElseThrow();
	}

	public Contribuinte updateContribuinte(Contribuinte update, long id) {
		Contribuinte obj = contribuinteRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(update, obj, "id");

		return contribuinteRepository.save(obj);

	}

}
