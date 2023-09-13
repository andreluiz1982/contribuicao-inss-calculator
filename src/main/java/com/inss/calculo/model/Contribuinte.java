package com.inss.calculo.model;

import java.util.List;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
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
	@NotBlank(message = "Nome não pode estar em branco")
	private String nomeCompleto;
	@NotBlank(message = "CPF não pode estar em branco")
	@CPF(message = "CPF deve ser válido")
	private String cpf;
	@Enumerated(EnumType.STRING)
	private RegimePrevidenciario regime;

	//@JsonIgnoreProperties({"contribuinteId"})
	@OneToMany(cascade = {CascadeType.REMOVE} ,mappedBy = "contribuinte" )
	private List<SalarioBaseContribuicao> salariosContribuicao;

	public Contribuinte(String nomeCompleto, String cpf, RegimePrevidenciario regime, List<SalarioBaseContribuicao> salariosContribuicao) {
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.regime = regime;
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

	@Override
	public String toString() {
		return "Contribuinte{" +
				"nomeCompleto='" + nomeCompleto + '\'' +
				", cpf='" + cpf + '\'' +
				'}';
	}
}
