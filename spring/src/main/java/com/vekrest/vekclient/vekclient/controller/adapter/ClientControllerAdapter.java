package com.vekrest.vekclient.vekclient.controller.adapter;

import com.vekrest.vekclient.entity.*;
import com.vekrest.vekclient.vekclient.controller.dto.request.ClientRequest;
import com.vekrest.vekclient.vekclient.controller.dto.request.ClientUpdateRequest;
import com.vekrest.vekclient.vekclient.controller.dto.response.ClientResponse;

import java.util.List;
import java.util.UUID;

public class ClientControllerAdapter {
    private ClientControllerAdapter() {
    }

    public static ClientResponse cast(Client client) {
        return new ClientResponse(
                client.id(),
                client.name(),
                client.birth().toString(),
                client.address()
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

    public static Pagination<ClientResponse> cast(Pagination<Client> pagination) {
        List<Client> clients = (List<Client>) pagination.content;

        return new Pagination<ClientResponse>(
                clients.stream().map(ClientControllerAdapter::cast).toList(),
                pagination.pageNumber,
                pagination.pageSize,
                pagination.totalElements,
                pagination.totalPages
        );
    }
}
