package com.vekrest.vekclient.controller.adapter;

import com.vekrest.entity.Address;
import com.vekrest.entity.Client;
import com.vekrest.entity.State;
import com.vekrest.entity.Status;
import com.vekrest.vekclient.controller.dto.request.ClientRequest;
import com.vekrest.vekclient.controller.dto.request.ClientUpdateRequest;
import com.vekrest.vekclient.controller.dto.response.ClientResponse;
import java.util.UUID;

public class ClientControllerAdapter {
    private ClientControllerAdapter() {
    }

    public static ClientResponse cast(Client client) {
        return new ClientResponse(
                client.id(),
                client.name(),
                client.birth().toString(),
                client.address(),
                client.status().getDescricao()
        );
    }

    public static Client cast(ClientRequest request) {
        return new Client(
                UUID.randomUUID().toString(),
                request.name(),
                request.birth(),
                new Address(
                    request.address().cep(),
                    State.porUf(request.address().state().toString())
                ),
                Status.ATIVO
        );
    }

    public static Client cast(ClientUpdateRequest request) {
        return new Client(
                null,
                request.name(),
                request.birth(),
                request.address() != null ? new Address(
                        request.address().cep(),
                        State.porUf(request.address().state().toString())
                ) : null,
                null
        );
    }
}
