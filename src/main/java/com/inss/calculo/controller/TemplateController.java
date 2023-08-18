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
		return "static/contribuinte.html";
	}
	@GetMapping("/static/contribuinte.js")
	public String getContribuinteJs() {
		return "/static/contribuinte.js";
	}
	
	
	@GetMapping("/page/salario-contribuicao")
	public String getSalarioContribuicao() {
		return "static/salario-contribuicao";
	}
	
	@GetMapping("/page/aliquota")
	public String getAliquota() {
		return "static/aliquota";
	}
	
	@GetMapping("/static/aliquota.js")
	public String getAliquotaJs() {
		return "static/aliquota.js";
	}
}