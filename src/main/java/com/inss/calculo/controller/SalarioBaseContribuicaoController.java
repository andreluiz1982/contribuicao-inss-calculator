package com.inss.calculo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.service.SalarioContribuicaoService;

@RestController
@RequestMapping("/salario-base")
public class SalarioBaseContribuicaoController {

	@Autowired
	private SalarioContribuicaoService contribuicaoService;
	
	@PostMapping
	public SalarioBaseContribuicao insertAliquota(@RequestBody SalarioBaseContribuicao obj  ) {
		
		return contribuicaoService.insertSalarioBaseContribuicao(obj);
	}
	@PutMapping("{id}")
	public SalarioBaseContribuicao updateFaixaAliquota(@RequestParam(name = "id") Long id,
											@RequestBody SalarioBaseContribuicao obj  ) {
		
		return contribuicaoService.updateSalarioBaseContribuicao(obj, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSalarioContribuicao(@RequestParam(name = "id") Long id ) {
		contribuicaoService.deleteSalarioBaseContribuicao(id);
		 
		 return ResponseEntity.noContent().build();
	}
	@GetMapping("/{id}")
	public SalarioBaseContribuicao getSalarioBaseContribuicao(@RequestParam(name = "id") Long id ) {
		return contribuicaoService.findById(id);
	}
	@GetMapping("/all")
	public List<SalarioBaseContribuicao> getAllSalarioBaseContribuicao() {
		return contribuicaoService.findAll();
	}
	
	
}
