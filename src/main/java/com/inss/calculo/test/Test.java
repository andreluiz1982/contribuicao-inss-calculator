package com.inss.calculo.test;

import java.math.BigDecimal;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;

import com.inss.calculo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.inss.calculo.dto.ContribuicaoMensalDTO;
import com.inss.calculo.repository.AliquotaRepository;
import com.inss.calculo.repository.ContribuinteRepository;
import com.inss.calculo.repository.SalarioBaseContribuicaoRepository;
import com.inss.calculo.service.ContribuicaoService;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private AliquotaRepository aliquotaRepository;

	@Autowired
	private ContribuicaoService contribuicaoService;

	@Autowired
	private ContribuinteRepository contribuinteRepository;

	@Autowired
	private SalarioBaseContribuicaoRepository salarioBaseContribuicaoRepository;

	@Value("${db.test}")
	private boolean test;

	@Override
	public void run(String... args) throws Exception {

		if(test) {
			
		Aliquota al1 = new Aliquota(YearMonth.of(2023, Month.MAY), Arrays.asList(
				new FaixaAliquota(BigDecimal.ZERO, new BigDecimal("1320.00"), new BigDecimal("7.5")),
				new FaixaAliquota(new BigDecimal("1320.01"), new BigDecimal("2571.29"), new BigDecimal("9.0")),
				new FaixaAliquota(new BigDecimal("2571.3"), new BigDecimal("3856.94"), new BigDecimal("12.0")),
				new FaixaAliquota(new BigDecimal("3856.95"), new BigDecimal("7507.49"), new BigDecimal("14.0"))));

		Aliquota al2 = new Aliquota(YearMonth.of(2023, Month.JANUARY), Arrays.asList(
				new FaixaAliquota(BigDecimal.ZERO, new BigDecimal("1302.00"), new BigDecimal("7.5")),
				new FaixaAliquota(new BigDecimal("1302.01"), new BigDecimal("2571.29"), new BigDecimal("9.0")),
				new FaixaAliquota(new BigDecimal("2571.30"), new BigDecimal("3856.94"), new BigDecimal("12.0")),
				new FaixaAliquota(new BigDecimal("3856.95"), new BigDecimal("7507.49"), new BigDecimal("14.0"))));

		Aliquota al3 = new Aliquota(YearMonth.of(2022, Month.JANUARY), Arrays.asList(
				new FaixaAliquota(BigDecimal.ZERO, new BigDecimal("1212.00"), new BigDecimal("7.5")),
				new FaixaAliquota(new BigDecimal("1212.01"), new BigDecimal("2427.35"), new BigDecimal("9.0")),
				new FaixaAliquota(new BigDecimal("2427.36"), new BigDecimal("3641.03"), new BigDecimal("12.0")),
				new FaixaAliquota(new BigDecimal("3641.04"), new BigDecimal("7087.22"), new BigDecimal("14.0"))));

		aliquotaRepository.saveAll(Arrays.asList(al1, al2, al3));

		Contribuinte c1 = new Contribuinte("Jonh Doe", "27088427060", RegimePrevidenciario.RPPS,null);
		Contribuinte c2 = new Contribuinte("Jonh Silver", "58244312050", RegimePrevidenciario.RGPS ,null);

		contribuinteRepository.saveAll(Arrays.asList(c1, c2));
		SalarioBaseContribuicao s1 = new SalarioBaseContribuicao(YearMonth.of(2023, 1), c1,
				Arrays.asList(new ComponenteIncidencia("teste1", new BigDecimal("2000.0"))));
		SalarioBaseContribuicao s2 = new SalarioBaseContribuicao(YearMonth.of(2023, 2), c1,
				Arrays.asList(new ComponenteIncidencia("teste", new BigDecimal("1900.0"))));
		SalarioBaseContribuicao s3 = new SalarioBaseContribuicao(YearMonth.of(2023, 3), c1,
				Arrays.asList(new ComponenteIncidencia("teste2", new BigDecimal("1902.0"))));
		;
		SalarioBaseContribuicao s4 = new SalarioBaseContribuicao(YearMonth.of(2018, 4), c1,
				Arrays.asList(new ComponenteIncidencia("teste3", new BigDecimal("1903.0"))));
		SalarioBaseContribuicao s5 = new SalarioBaseContribuicao(YearMonth.of(2023, 5), c1,
				Arrays.asList(
						new ComponenteIncidencia("salario base", new BigDecimal("1910.0")),
						new ComponenteIncidencia("1/3 ferias", new BigDecimal("1910.0")),
						new ComponenteIncidencia("13", new BigDecimal("1910.0")),
						new ComponenteIncidencia("insalubridade", new BigDecimal("1910.0"))

				));
		SalarioBaseContribuicao s6 = new SalarioBaseContribuicao(YearMonth.of(2023, 6), c1,
				Arrays.asList(new ComponenteIncidencia("teste5", new BigDecimal("1913.0"))));

		salarioBaseContribuicaoRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));

		ContribuicaoMensalDTO dto = contribuicaoService.calculaContribuicao(s1);
//		System.err.println(dto);
		}
	}

}
