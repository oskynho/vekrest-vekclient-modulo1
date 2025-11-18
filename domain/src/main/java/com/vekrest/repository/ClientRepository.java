package com.vekrest.repository;

import com.vekrest.entity.Client;
import com.vekrest.entity.Pagination;

public interface ClientRepository {
    Pagination<Client> getAll(int pageNumber, int pageSize);

    Client save(Client client);

    Client findById(String id);

    void delete(String id);
}
