package com.inss.calculo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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

	@JsonIgnoreProperties({"contribuinte"})
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

	@Override
	public String toString() {
		return "Contribuinte{" +
				"nomeCompleto='" + nomeCompleto + '\'' +
				", cpf='" + cpf + '\'' +
				'}';
	}
}
