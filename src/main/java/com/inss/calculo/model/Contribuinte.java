package com.inss.calculo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contribuinte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeCompleto;
		
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contribuinte" )
	private List<SalarioBaseContribuicao> salariosContribuicao;

	public Contribuinte(String nomeCompleto, String cpf, List<SalarioBaseContribuicao> salariosContribuicao) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.salariosContribuicao = salariosContribuicao;
	}

	public Contribuinte(String nomeCompleto, String cpf) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
	}

	public Contribuinte(Long id) {
		super();
		this.id = id;
	}
	
	
	
	
}
