package com.inss.calculo.dto.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inss.calculo.dto.AliquotaDTO;
import com.inss.calculo.model.Aliquota;

@Component
public class AliquotaAssembler {
	@Autowired
	private ModelMapper mapper = new ModelMapper();
	
	public AliquotaDTO makeDTO(Aliquota obj) {
		return this.mapper.map(obj, AliquotaDTO.class);
	}

}
