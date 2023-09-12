package com.inss.calculo.dto;

import java.util.List;

import com.inss.calculo.model.FaixaAliquota;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AliquotaDTO {


	private Long id;
	
	private String anoMes;
	
	private List<FaixaAliquota> faixasAliquotas;

	
	
}
