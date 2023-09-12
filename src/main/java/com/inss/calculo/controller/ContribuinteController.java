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

import com.inss.calculo.dto.ContribuinteDTO;
import com.inss.calculo.dto.assembler.ContribuinteAssembler;
import com.inss.calculo.model.Contribuinte;
import com.inss.calculo.service.ContribuinteService;

@RestController
@RequestMapping("/contribuinte")
public class ContribuinteController {

	@Autowired
	private ContribuinteService contribuinteService;
	
	@Autowired
	private ContribuinteAssembler contribuinteAssembler;
	
	@PostMapping
	public Contribuinte insertContribuinte(@RequestBody Contribuinte contribuinte  ) {
		return contribuinteService.insertContribuinte(contribuinte);
	}
	@PutMapping("/{id}")
	public Contribuinte updateContribuinte(@PathVariable(name = "id") Long id,
											@RequestBody Contribuinte contribuinte  ) {
		return contribuinteService.updateContribuinte(contribuinte, id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContribuinte(@PathVariable(name = "id") Long id ) {
		 contribuinteService.deleteContribuinte(id);
		 
		 return ResponseEntity.noContent().build();
	}
	@GetMapping("/{id}")
	public ContribuinteDTO getContribuinte(@PathVariable(name = "id") Long id ) {
		return contribuinteAssembler.makeDTO(contribuinteService.findById(id));
	}
	@GetMapping("/all")
	public List<ContribuinteDTO> getContribuinte() {
		return contribuinteAssembler.makeDTO(contribuinteService.findAll());
	}
	
	
}
