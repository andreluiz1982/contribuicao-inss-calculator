package com.inss.calculo.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor()
public class FaixaAliquota implements Comparable<FaixaAliquota>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Valor Mínimo não pode ser nulo")
	@PositiveOrZero(message = "Valor mínimo deve ser zero ou valor positivo")
	private BigDecimal valorMinimo;
	
	@NotNull(message = "Valor Máximo não pode ser nulo")
	@Positive(message = "Valor Máximo deve ser maior que zero")
	private BigDecimal valorMaximo;
	
	@NotNull(message = "Alíquota não pode ser nula")
	@PositiveOrZero(message = "Aliquota deve ser zero ou valor positivo")
	private BigDecimal aliquota;
	
	

	@Override
	public int compareTo(FaixaAliquota o) {
		return getValorMinimo().compareTo(o.valorMinimo);
	}


	public FaixaAliquota(BigDecimal valorMinimo, BigDecimal valorMaximo, BigDecimal aliquota) {
		super();
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.aliquota = aliquota;
	}


	@Override
	public String toString() {
		return "FaixaAliquota{" +
				"id=" + id +
				", valorMinimo=" + valorMinimo +
				", valorMaximo=" + valorMaximo +
				", aliquota=" + aliquota +
				'}';
	}
}
