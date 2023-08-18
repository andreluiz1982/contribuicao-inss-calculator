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
@AllArgsConstructor()
public class FaixaAliquota implements Comparable<FaixaAliquota>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private BigDecimal valorMinimo;
	@NotNull
	private BigDecimal valorMaximo;
	@NotNull
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




	
	
	

}
