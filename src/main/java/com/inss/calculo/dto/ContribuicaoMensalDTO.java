package com.inss.calculo.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.List;

import lombok.Data;

@Data
public class ContribuicaoMensalDTO {
	
	private String anoMes;
	private List<FaixaContribuicaoDTO> faixaContribuicao;
	
	private BigDecimal totalDevidoMes;

	public ContribuicaoMensalDTO(YearMonth anoMes, List<FaixaContribuicaoDTO> faixaContribuicao, BigDecimal totalDevidoMes) {
		super();
		this.anoMes = anoMes.toString();
		this.faixaContribuicao = faixaContribuicao;
		this.totalDevidoMes = totalDevidoMes.setScale(3, RoundingMode.HALF_EVEN);
	}
	



	

}
