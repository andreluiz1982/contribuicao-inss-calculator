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

import com.inss.calculo.model.Aliquota;
import com.inss.calculo.service.AliquotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aliquota")
public class AliquotaController {

	@Autowired
	private AliquotaService aliquotaService;

	@PostMapping
	public Aliquota insertAliquota(@Valid @RequestBody Aliquota obj) {
		System.err.println(obj);
		return aliquotaService.insertAliquota(obj);
	}

	@PostMapping("/all")
	public List<Aliquota> insertListAliquota(@RequestBody List<Aliquota> obj) {

		return aliquotaService.insertListAliquota(obj);
	}

	@PutMapping("/{id}")
	public Aliquota updateAliquota(@PathVariable(name = "id") Long id, @RequestBody Aliquota obj) {

		return aliquotaService.updateAliquota(obj, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAliquota(@PathVariable(name = "id") Long id) {
		aliquotaService.deleteAliquota(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public Aliquota getAliquota(@PathVariable(name = "id") Long id) {
		return aliquotaService.findById(id);
	}

	@GetMapping("/all")
	public List<Aliquota> getAliquota() {
		return aliquotaService.findAll();
	}

}
