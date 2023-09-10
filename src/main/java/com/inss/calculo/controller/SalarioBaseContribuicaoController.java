package com.inss.calculo.controller;

import java.util.List;

import com.inss.calculo.dto.ContribuicaoMensalDTO;
import com.inss.calculo.dto.ContribuicaoTotalDTO;
import com.inss.calculo.model.Contribuinte;
import com.inss.calculo.service.ContribuicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.service.SalarioContribuicaoService;

@RestController
@RequestMapping("/salario-base")
public class SalarioBaseContribuicaoController {

	@Autowired
	private SalarioContribuicaoService salarioContribuicaoService;
	@Autowired
	private ContribuicaoService contribuicaoService;

	@PostMapping
	public SalarioBaseContribuicao insertAliquota(@RequestBody @Valid SalarioBaseContribuicao obj) {
		System.err.println(obj);
		return salarioContribuicaoService.insertSalarioBaseContribuicao(obj);
	}

	@PutMapping("/{id}")
	public SalarioBaseContribuicao updateFaixaAliquota(@PathVariable(name = "id") Long id,
			@RequestBody @Valid SalarioBaseContribuicao obj) {

		return salarioContribuicaoService.updateSalarioBaseContribuicao(obj, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSalarioContribuicao(@PathVariable(name = "id") Long id) {
		salarioContribuicaoService.deleteSalarioBaseContribuicao(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public SalarioBaseContribuicao getSalarioBaseContribuicao(@PathVariable(name = "id") Long id) {
		return salarioContribuicaoService.findById(id);
	}

	@GetMapping("/all")
	public List<SalarioBaseContribuicao> getAllSalarioBaseContribuicao() {
		return salarioContribuicaoService.findAll();
	}

	@GetMapping("/all/calculo/{id}")
	public ContribuicaoTotalDTO calculateContribuicaoPerContribuinte(@PathVariable(name = "id") Long contribuinteId) {

		List<SalarioBaseContribuicao> salarios = salarioContribuicaoService
				.getContribuicoesPerContribuinte(contribuinteId);
		List<ContribuicaoMensalDTO> dtos = salarios.stream().map(s -> this.contribuicaoService.calculaContribuicao(s))
				.toList();
		if (!salarios.isEmpty()) {
			return new ContribuicaoTotalDTO(dtos, salarios.get(0).getContribuinte());
		} else {
			return new ContribuicaoTotalDTO(dtos, new Contribuinte(contribuinteId));

		}
	}

}
