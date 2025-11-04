package com.vekrest.repository;

import com.vekrest.entity.Client;
import java.util.List;

public interface ClientRepository {
    List<Client> getAll();

    Client save(Client client);

    Client findById(String id);

    void delete(String id);
}
