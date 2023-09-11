package com.inss.calculo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inss.calculo.configuration.YearMonthIntegerAttributeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalarioBaseContribuicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "O ano-mês é obrigatório no formato yyyy-dd. Ex, 2015-08")
	@Convert(
			converter = YearMonthIntegerAttributeConverter.class
	)
	private YearMonth anoMes;

	@NotNull(message = "Selecione o contribuinte deve ser indicado")
	@JsonIgnoreProperties({ "salariosContribuicao" })
	@ManyToOne(optional = false)
	private Contribuinte contribuinte;

	@OneToMany(cascade = CascadeType.ALL)
	@NotNull(message = "Deve haver pelo menos um componente")
	@Column(name = "_componentes_incidencia")
	private List<ComponenteIncidencia> componentesIncidencia = new ArrayList<>();

	public SalarioBaseContribuicao(YearMonth anoMes, Contribuinte contribuinte,
			List<ComponenteIncidencia> componentesIncidencia) {
		super();
		this.anoMes = anoMes;
		this.contribuinte = contribuinte;
		this.componentesIncidencia = componentesIncidencia;
	}

	@Override
	public String toString() {
		return "SalarioBaseContribuicao{" + "anoMes=" + anoMes + ", componentesIncidencia=" + componentesIncidencia
				+ '}';
	}
}
