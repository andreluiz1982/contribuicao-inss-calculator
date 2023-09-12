package com.inss.calculo.dto.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inss.calculo.dto.SalarioBaseContribuicaoDTO;
import com.inss.calculo.model.SalarioBaseContribuicao;

@Component
public class SalarioBaseAssembler {
	@Autowired
	private ModelMapper mapper = new ModelMapper();
	
	public SalarioBaseContribuicaoDTO makeDTO(SalarioBaseContribuicao obj) {
		return this.mapper.map(obj, SalarioBaseContribuicaoDTO.class);
	}

}
