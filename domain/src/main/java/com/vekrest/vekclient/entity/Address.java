package com.vekrest.vekclient.entity;

public record Address(
        String cep,
        State state
) {}