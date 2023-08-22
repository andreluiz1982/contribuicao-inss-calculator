package com.inss.calculo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FaixaContribuicaoDTO {
	
	private String nomeFaixa;
	private BigDecimal totalFaixa;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(nomeFaixa);
		builder.append(totalFaixa);
		return builder.toString();
	}
	public FaixaContribuicaoDTO(String nomeFaixa, BigDecimal totalFaixa) {
		super();
		this.nomeFaixa = nomeFaixa;
		this.totalFaixa = totalFaixa;
	}
	
	

}
