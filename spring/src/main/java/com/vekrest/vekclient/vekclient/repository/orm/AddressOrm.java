package com.vekrest.vekclient.vekclient.repository.orm;

import com.vekrest.vekclient.entity.State;
import org.springframework.data.mongodb.core.index.Indexed;

public record AddressOrm(
        @Indexed
        String cep,
        State state
) {
}
