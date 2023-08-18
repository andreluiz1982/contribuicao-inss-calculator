package com.inss.calculo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inss.calculo.model.Aliquota;
import com.inss.calculo.repository.AliquotaRepository;

@Service
public class AliquotaService {

	@Autowired
	private AliquotaRepository aliquotaRepository;
	
	public Aliquota insertAliquota(Aliquota aliquota) {
		
	 return	aliquotaRepository.save(aliquota);
	}
	
	public List<Aliquota> insertListAliquota(List<Aliquota> aliquota) {
		
		 return	aliquotaRepository.saveAll(aliquota);
		}
	
	public void deleteAliquota(Long id) {
		aliquotaRepository.deleteById(id);
	}
	
	public List<Aliquota> findAll(){
		return aliquotaRepository.findAll();
	}
	
	public Aliquota updateAliquota(Aliquota update, long id) {
		Aliquota aliquota = aliquotaRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(update, aliquota, "id");
		
		return aliquotaRepository.save(aliquota);
		
		
		
		
	}

	public Aliquota findById(Long id) {
		return aliquotaRepository.findById(id).orElseThrow();
	}
	
}
