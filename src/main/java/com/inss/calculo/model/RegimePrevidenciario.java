package com.inss.calculo.model;

import lombok.Getter;

@Getter
public enum RegimePrevidenciario {

    RGPS("REGIME GERAL DA PREVIDENCIA SOCIAL"),
    RPPS("REGIME PRÓPRIO DE PREVIDENCIA SOCIAL"),
    RPCSP("REGIME DE PREVIDENCIA COMPLEMENTAR DOS SERVIDORES PUBLICOS"),
    RPCP("REGIME DE PREVIDENCIA COMPLEMENTAR PRIVADA COMPLEMENTAR");

    private String descricao;

    RegimePrevidenciario(String descricao) {
        this.descricao = descricao;
    }
}
