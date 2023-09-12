package com.inss.calculo.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ContribuicaoTotalDTO {

    private ContribuinteDTO contribuinte;
    private List<ContribuicaoMensalDTO> contribuicaoMensal;
    private BigDecimal totalDevido;

    public ContribuicaoTotalDTO(List<ContribuicaoMensalDTO> mensal, ContribuinteDTO contribuinte) {
        this.contribuinte = contribuinte;
        this.contribuicaoMensal = mensal;
        this.totalDevido = mensal.stream().map(m -> m.getTotalDevidoMes())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
