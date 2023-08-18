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

import com.inss.calculo.model.FaixaAliquota;
import com.inss.calculo.service.FaixaAliquotaService;

@RestController
@RequestMapping("/faixa-aliquota")
public class FaixaAliquotaController {

	@Autowired
	private FaixaAliquotaService faixaAliquotaService;

	@PostMapping
	public FaixaAliquota insertAliquota(@RequestBody FaixaAliquota obj) {

		return faixaAliquotaService.insertAliquota(obj);
	}

	@PutMapping("{id}")
	public FaixaAliquota updateFaixaAliquota(@RequestParam(name = "id") Long id, @RequestBody FaixaAliquota obj) {

		return faixaAliquotaService.updateAliquota(obj, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFaixaAliquota(@RequestParam(name = "id") Long id) {
		faixaAliquotaService.deleteAliquota(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public FaixaAliquota getFaixaAliquota(@RequestParam(name = "id") Long id) {
		return faixaAliquotaService.findById(id);
	}

	@GetMapping("/all")
	public List<FaixaAliquota> getFaixaAliquota() {
		return faixaAliquotaService.findAll();
	}

}
