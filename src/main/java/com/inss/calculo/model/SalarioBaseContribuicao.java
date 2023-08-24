package com.inss.calculo.model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioBaseContribuicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private YearMonth anoMes;

	private String descricao;

	@JsonIgnore
	@ManyToOne (optional = false, cascade = CascadeType.PERSIST)
	private Contribuinte contribuinte;
	
	private List<BigDecimal> componentesIncidencia = new ArrayList<>();

	public SalarioBaseContribuicao(YearMonth anoMes, Contribuinte contribuinte,
			List<BigDecimal> componentesIncidencia) {
		super();
		this.anoMes = anoMes;
		this.contribuinte = contribuinte;
		this.componentesIncidencia = componentesIncidencia;
	}

	public SalarioBaseContribuicao(YearMonth anoMes, String descricao, Contribuinte contribuinte, List<BigDecimal> componentesIncidencia) {
		this.anoMes = anoMes;
		this.descricao = descricao;
		this.contribuinte = contribuinte;
		this.componentesIncidencia = componentesIncidencia;
	}
}
