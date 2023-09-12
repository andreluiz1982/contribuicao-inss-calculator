package com.inss.calculo.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContribuinteDTO {

	private Long id;
	private String nomeCompleto;
	private String cpf;
	private List<SalarioBaseContribuicaoDTO> salariosContribuicao;
	public ContribuinteDTO(Long id) {
		super();
		this.id = id;
	}
	
	
}
