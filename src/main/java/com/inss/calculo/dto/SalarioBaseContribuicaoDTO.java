package com.inss.calculo.dto;

import java.util.ArrayList;
import java.util.List;

import com.inss.calculo.model.ComponenteIncidencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalarioBaseContribuicaoDTO {
	
	private Long id;

	private String anoMes;

	private Long contribuinteId;

	private List<ComponenteIncidencia> componentesIncidencia = new ArrayList<>();

	public SalarioBaseContribuicaoDTO(String anoMes, Long contribuinteId,
			List<ComponenteIncidencia> componentesIncidencia) {
		super();
		this.anoMes = anoMes;
		this.contribuinteId = contribuinteId;
		this.componentesIncidencia = componentesIncidencia;
	}

	

}
