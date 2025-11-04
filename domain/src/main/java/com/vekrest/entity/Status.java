package com.vekrest.entity;

public enum Status {
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}