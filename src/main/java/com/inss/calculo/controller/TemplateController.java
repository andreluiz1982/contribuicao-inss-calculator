package com.inss.calculo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TemplateController {

	@GetMapping()
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/page/contribuinte")
	public String getContribuinte() {
		return "contribuinte.html";
	}
	@GetMapping("/static/contribuinte.js")
	public String getContribuinteJs() {
		return "/static/contribuinte.js";
	}
	
	
	@GetMapping("/page/salarios-contribuicao")
	public String getSalarioContribuicao() {
		return "salarios";
	}
	
	@GetMapping("/page/aliquota")
	public String getAliquota() {
		return "aliquota";
	}
	
	@GetMapping("aliquota.js")
	public String getAliquotaJs() {
		return "/static/aliquota.js";
	}

	@GetMapping("/static/salarios.js")
	public String getSalariosJs() {
		return "/static/salarios.js";
	}
}