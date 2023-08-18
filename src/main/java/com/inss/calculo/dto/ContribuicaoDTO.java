package com.inss.calculo.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.List;

import lombok.Data;

@Data
public class ContribuicaoDTO {
	
	private YearMonth mes;
	private List<FaixaContribuicaoDTO> faixaContribuicao;
	
	private BigDecimal totalDevidoMes;

	public ContribuicaoDTO(YearMonth mes, List<FaixaContribuicaoDTO> faixaContribuicao, BigDecimal totalDevidoMes) {
		super();
		this.mes = mes;
		this.faixaContribuicao = faixaContribuicao;
		this.totalDevidoMes = totalDevidoMes.setScale(3, RoundingMode.HALF_EVEN);
	}
	



	

}
