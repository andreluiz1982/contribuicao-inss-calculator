package com.inss.calculo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inss.calculo.configuration.YearMonthIntegerAttributeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aliquota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@JsonIgnore
	private Long id;
	
	@Column(unique = true)
	@NotNull
	@Convert(
			converter = YearMonthIntegerAttributeConverter.class
	)
	private YearMonth anoMes;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@NotEmpty(message = "Deve conter pelo menos uma faixa de alíquota válida")
	private List<FaixaAliquota> faixasAliquotas;

	public Aliquota(YearMonth anoMes, List<FaixaAliquota> faixasAliquotas) {
		super();
		this.anoMes = anoMes;
		this.faixasAliquotas = faixasAliquotas;
	}

	@Override
	public String toString() {
		return "Aliquota{" +
				"anoMes=" + anoMes +
				", faixasAliquotas=" + faixasAliquotas +
				'}';
	}
}
