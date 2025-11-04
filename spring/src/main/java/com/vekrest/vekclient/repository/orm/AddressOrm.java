package com.vekrest.vekclient.repository.orm;

import com.vekrest.entity.State;
import org.springframework.data.mongodb.core.index.Indexed;

public record AddressOrm(
        @Indexed
        String cep,
        State state
) {
}
