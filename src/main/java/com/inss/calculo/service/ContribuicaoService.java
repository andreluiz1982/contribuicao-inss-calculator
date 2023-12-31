package com.inss.calculo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inss.calculo.dto.ContribuicaoMensalDTO;
import com.inss.calculo.dto.FaixaContribuicaoDTO;
import com.inss.calculo.model.Aliquota;
import com.inss.calculo.model.FaixaAliquota;
import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.repository.AliquotaRepository;

@Component
public class ContribuicaoService {

	@Autowired
	private AliquotaRepository aliquotaRepository;

	public ContribuicaoMensalDTO calculaContribuicao(SalarioBaseContribuicao salario) {

		List<Aliquota> aliquotas = aliquotaRepository.findAll();
		Aliquota al = findAliquotaIncidente(aliquotas, salario);

//		
		List<FaixaAliquota> faixas;
		try {
			faixas = al.getFaixasAliquotas();
			return calculaValorPorFaixa(salario, al, faixas);
		} catch (Exception e) {
			throw new RuntimeException(String.format(
					"Não existe faixa de alíquota anterior " + 
							"ao salário de contribuição do ano-mês %s. Adicione a alíquota incidente.",
					salario.getAnoMes().toString()));
		}

	}

	private ContribuicaoMensalDTO calculaValorPorFaixa(SalarioBaseContribuicao salario, Aliquota al,
			List<FaixaAliquota> faixas) {

		Collections.sort(faixas);

		BigDecimal sal = salario.getComponentesIncidencia().stream().map(b -> b.getValorComponente())
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal contribuicao = BigDecimal.ZERO;

		List<FaixaContribuicaoDTO> faixasDtos = new ArrayList<>();

		int count = 0;

		for (FaixaAliquota fa : al.getFaixasAliquotas()) {
			count++;
			BigDecimal max = fa.getValorMaximo();
			BigDecimal min = fa.getValorMinimo();
			BigDecimal aliq = fa.getAliquota().divide(new BigDecimal("100.0"), 12, RoundingMode.HALF_EVEN);

			if (sal.compareTo(min) > 0 && sal.compareTo(max) > 0) {
				contribuicao = contribuicao.add((max.subtract(min)).multiply(aliq));

			}
			if (sal.compareTo(max) < 0 && sal.compareTo(min) > 0) {
				contribuicao = contribuicao.add((sal.subtract(min)).multiply(aliq));
			}


			faixasDtos.add(new FaixaContribuicaoDTO(("Faixa " + count + ": "),
					contribuicao.setScale(3, RoundingMode.HALF_EVEN)));
		}

		return new ContribuicaoMensalDTO(salario.getAnoMes(), faixasDtos, contribuicao);
	}

	private Aliquota findAliquotaIncidente(List<Aliquota> aliquotas, SalarioBaseContribuicao salario)
			throws NullPointerException {
		YearMonth mesSalario = YearMonth.from(salario.getAnoMes());

		Aliquota ultAnterior = new Aliquota();
		for (Aliquota a : aliquotas) {

			if (a.getAnoMes().isBefore(mesSalario) || a.getAnoMes().equals(mesSalario)
					&& (ultAnterior.getAnoMes() == null ||
					a.getAnoMes().isAfter(ultAnterior.getAnoMes()))
			) {
				ultAnterior = a;
			}

		}

			return ultAnterior;

	}

}
