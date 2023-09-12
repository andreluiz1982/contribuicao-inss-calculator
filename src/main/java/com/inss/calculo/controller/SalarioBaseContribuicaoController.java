package com.inss.calculo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inss.calculo.dto.ContribuicaoMensalDTO;
import com.inss.calculo.dto.ContribuicaoTotalDTO;
import com.inss.calculo.dto.ContribuinteDTO;
import com.inss.calculo.dto.SalarioBaseContribuicaoDTO;
import com.inss.calculo.dto.assembler.ContribuinteAssembler;
import com.inss.calculo.dto.assembler.SalarioBaseAssembler;
import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.service.ContribuicaoService;
import com.inss.calculo.service.SalarioContribuicaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/salario-base")
public class SalarioBaseContribuicaoController {

	@Autowired
	private SalarioContribuicaoService salarioContribuicaoService;
	@Autowired
	private ContribuicaoService contribuicaoService;
	
	@Autowired
	private ContribuinteAssembler contribuinteAssembler;
	
	@Autowired
	private SalarioBaseAssembler salarioBaseAssembler;

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
	public SalarioBaseContribuicaoDTO getSalarioBaseContribuicao(@PathVariable(name = "id") Long id) {
		return salarioBaseAssembler.makeDTO(salarioContribuicaoService.findById(id));
	}

	@GetMapping("/all")
	public List<SalarioBaseContribuicaoDTO> getAllSalarioBaseContribuicao() {
		return salarioContribuicaoService.findAll().stream()
				.map(r -> this.salarioBaseAssembler.makeDTO(r)).toList();
	}

	@GetMapping("/all/calculo/{id}")
	public ContribuicaoTotalDTO calculateContribuicaoPerContribuinte(@PathVariable(name = "id") Long contribuinteId) {

		List<SalarioBaseContribuicao> salarios = salarioContribuicaoService
				.getContribuicoesPerContribuinte(contribuinteId);
		List<ContribuicaoMensalDTO> dtos = salarios.stream().map(s -> this.contribuicaoService.calculaContribuicao(s))
				.toList();
		if (!salarios.isEmpty()) {
			return new ContribuicaoTotalDTO(dtos, contribuinteAssembler.makeDTO(salarios.get(0).getContribuinte()));
		} else {
			return new ContribuicaoTotalDTO(dtos, new ContribuinteDTO(contribuinteId));

		}
	}

}
