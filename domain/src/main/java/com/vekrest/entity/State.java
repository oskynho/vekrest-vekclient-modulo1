package com.vekrest.entity;

import com.vekrest.exception.NotFoundException;

public enum State {
    AC(12, "AC", "Acre"),
    AL(27, "AL", "Alagoas"),
    AP(16, "AP", "Amapá"),
    AM(13, "AM", "Amazonas"),
    BA(29, "BA", "Bahia"),
    CE(23, "CE", "Ceará"),
    DF(53, "DF", "Distrito Federal"),
    ES(32, "ES", "Espírito Santo"),
    GO(52, "GO", "Goiás"),
    MA(21, "MA", "Maranhão"),
    MT(51, "MT", "Mato Grosso"),
    MS(50, "MS", "Mato Grosso do Sul"),
    MG(31, "MG", "Minas Gerais"),
    PA(15, "PA", "Pará"),
    PB(25, "PB", "Paraíba"),
    PR(41, "PR", "Paraná"),
    PE(26, "PE", "Pernambuco"),
    PI(22, "PI", "Piauí"),
    RJ(33, "RJ", "Rio de Janeiro"),
    RN(24, "RN", "Rio Grande do Norte"),
    RS(43, "RS", "Rio Grande do Sul"),
    RO(11, "RO", "Rondônia"),
    RR(14, "RR", "Roraima"),
    SC(42, "SC", "Santa Catarina"),
    SP(35, "SP", "São Paulo"),
    SE(28, "SE", "Sergipe"),
    TO(17, "TO", "Tocantins");

    private final int codigo;
    private final String uf;
    private final String nome;

    State(int codigo, String uf, String nome) {
        this.codigo = codigo;
        this.uf = uf;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getUf() {
        return uf;
    }

    public String getNome() {
        return nome;
    }

    public static State porUf(String uf) {
        for (State estado : values()) {
            if (estado.uf.equalsIgnoreCase(uf)) {
                return estado;
            }
        }
        throw new NotFoundException("Estado não existe");
    }
}