package com.inss.calculo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComponenteIncidencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@NotNull(message = "O valor não pode ser nulo")
	private BigDecimal valorComponente;

	public ComponenteIncidencia(String descricao, @NotNull BigDecimal valorComponente) {
		super();
		this.descricao = descricao;
		this.valorComponente = valorComponente;
	}

	@Override
	public String toString() {
		return "ComponenteIncidencia{" +
				"descricao='" + descricao + '\'' +
				", valorComponente=" + valorComponente +
				'}';
	}
}
