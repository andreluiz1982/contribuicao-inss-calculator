package com.inss.calculo.dto.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inss.calculo.dto.ContribuinteDTO;
import com.inss.calculo.model.Contribuinte;

@Component
public class ContribuinteAssembler {
	@Autowired
	private ModelMapper mapper = new ModelMapper();
	
	public ContribuinteDTO makeDTO(Contribuinte obj) {
		return this.mapper.map(obj, ContribuinteDTO.class);
	}
	
	public List<ContribuinteDTO> makeDTO(List<Contribuinte> obj) {
		return obj.stream().map(c -> makeDTO(c)).toList();
	}

}
