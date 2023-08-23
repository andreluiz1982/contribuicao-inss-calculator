package com.inss.calculo.test;

import java.math.BigDecimal;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.inss.calculo.dto.ContribuicaoDTO;
import com.inss.calculo.model.Aliquota;
import com.inss.calculo.model.Contribuinte;
import com.inss.calculo.model.FaixaAliquota;
import com.inss.calculo.model.SalarioBaseContribuicao;
import com.inss.calculo.repository.AliquotaRepository;
import com.inss.calculo.repository.ContribuinteRepository;
import com.inss.calculo.service.ContribuicaoService;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private AliquotaRepository aliquotaRepository;
	
	@Autowired
	private ContribuicaoService contribuicaoService;
	
	@Autowired
	private ContribuinteRepository contribuinteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		

		
		Aliquota al1 = new Aliquota( YearMonth.of(2023, Month.MAY), Arrays.asList(
				 new FaixaAliquota(BigDecimal.ZERO, new BigDecimal("1320.00"), new BigDecimal("7.5")),
				 new FaixaAliquota(new BigDecimal("1320.01"), new BigDecimal("2571.29"), new BigDecimal("9.0")),
				 new FaixaAliquota(new BigDecimal("2571.3"), new BigDecimal("3856.94"), new BigDecimal("12.0")),
				 new FaixaAliquota(new BigDecimal("3856.95"), new BigDecimal("7507.49"), new BigDecimal("14.0"))
				));
		
		Aliquota al2 = new Aliquota( YearMonth.of(2023, Month.JANUARY), Arrays.asList(
				 new FaixaAliquota(BigDecimal.ZERO, new BigDecimal("1302.00"), new BigDecimal("7.5")),
				 new FaixaAliquota(new BigDecimal("1302.01"), new BigDecimal("2571.29"), new BigDecimal("9.0")),
				 new FaixaAliquota(new BigDecimal("2571.30"), new BigDecimal("3856.94"), new BigDecimal("12.0")),
				 new FaixaAliquota(new BigDecimal("3856.95"), new BigDecimal("7507.49"), new BigDecimal("14.0"))
				));
		
		Aliquota al3 = new Aliquota( YearMonth.of(2022, Month.JANUARY), Arrays.asList(
				 new FaixaAliquota(BigDecimal.ZERO, new BigDecimal("1212.00"), new BigDecimal("7.5")),
				 new FaixaAliquota(new BigDecimal("1212.01"), new BigDecimal("2427.35"), new BigDecimal("9.0")),
				 new FaixaAliquota(new BigDecimal("2427.36"), new BigDecimal("3641.03"), new BigDecimal("12.0")),
				 new FaixaAliquota(new BigDecimal("3641.04"), new BigDecimal("7087.22"), new BigDecimal("14.0"))
				));

		aliquotaRepository.saveAll(Arrays.asList(al1, al2, al3));
		
		Contribuinte c1 = new Contribuinte("Jonh Doe", "12345678901", null);
		Contribuinte c2 = new Contribuinte("Jonh Silver", "10987654321", null);

		SalarioBaseContribuicao s1 = new SalarioBaseContribuicao(YearMonth.of(2023, 1), c1, Arrays.asList(new BigDecimal("2000.0")));
//		s1.setContribuinte(c1);
	
		
		ContribuicaoDTO dto = contribuicaoService.calculaContribuicao(s1);
		contribuinteRepository.saveAll(Arrays.asList(c1,c2));
		System.err.println(dto);
	}

}
