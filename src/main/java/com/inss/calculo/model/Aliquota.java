package com.inss.calculo.model;

import java.time.YearMonth;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aliquota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(unique = true)
	@NotNull
	private YearMonth anoMes;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@NotNull
	private List<FaixaAliquota> faixasAliquotas;

	public Aliquota(YearMonth anoMes, List<FaixaAliquota> faixasAliquotas) {
		super();
		this.anoMes = anoMes;
		this.faixasAliquotas = faixasAliquotas;
	}
	

}
