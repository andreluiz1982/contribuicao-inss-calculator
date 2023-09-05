package com.inss.calculo.dto;

import com.inss.calculo.model.Contribuinte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ContribuicaoTotalDTO {

    private Contribuinte contribuinte;
    private List<ContribuicaoMensalDTO> contribuicaoMensal;
    private BigDecimal totalDevido;

    public ContribuicaoTotalDTO(List<ContribuicaoMensalDTO> mensal, Contribuinte contribuinte) {
        this.contribuinte = contribuinte;
        this.contribuicaoMensal = mensal;
        this.totalDevido = mensal.stream().map(m -> m.getTotalDevidoMes())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
