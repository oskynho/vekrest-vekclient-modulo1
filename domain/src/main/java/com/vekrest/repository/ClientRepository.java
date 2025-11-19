package com.vekrest.repository;

import com.vekrest.entity.Client;
import com.vekrest.entity.Pagination;

public interface ClientRepository {
    Pagination<Client> getAll(final int pageNumber, final int pageSize);

    Client save(Client client);

    Client findById(final String id);

    void delete(final String id);
}
