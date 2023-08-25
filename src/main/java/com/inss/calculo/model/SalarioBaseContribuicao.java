package com.inss.calculo.model;

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
import jakarta.persistence.OneToMany;
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

	@JsonIgnore
	@ManyToOne (optional = false)
	private Contribuinte contribuinte;
	
	@OneToMany(cascade = CascadeType.ALL )
	private List<ComponenteIncidencia> componentesIncidencia = new ArrayList<>();

	public SalarioBaseContribuicao(YearMonth anoMes, Contribuinte contribuinte,
			List<ComponenteIncidencia> componentesIncidencia) {
		super();
		this.anoMes = anoMes;
		this.contribuinte = contribuinte;
		this.componentesIncidencia = componentesIncidencia;
	}


}
