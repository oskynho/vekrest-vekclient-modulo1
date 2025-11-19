package com.vekrest.service;

import com.vekrest.entity.Client;
import com.vekrest.entity.Pagination;
import com.vekrest.entity.Status;
import com.vekrest.exception.InternalServerException;
import com.vekrest.exception.NotFoundException;
import com.vekrest.repository.ClientRepository;

public class ClientService {

    private final ClientRepository repository;

    public ClientService(
            ClientRepository repository
    ) {
        this.repository = repository;
    }

    public Client register(Client client) {
        try {
            Client updateClient = repository.findById(client.id());
            return save(updateClient.id(), client);
        } catch (NotFoundException ex) {
            return save(client.id(), client);
        }
    }

    public Client update(final String id, Client client) {
        Client updateClient = repository.findById(id);

        return save(updateClient.id(), new Client(
                id,
                client.name() != null ? client.name() : updateClient.name(),
                client.birth() != null ? client.birth() : updateClient.birth(),
                client.address() != null ? client.address() : updateClient.address(),
                client.status() != null ? client.status() : updateClient.status()
        ));
    }

    private Client save(final String id, Client client) {
        try {
            return repository.save(new Client(
                    id,
                    client.name(),
                    client.birth(),
                    client.address(),
                    client.status()
            ));
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }

    public Pagination<Client> getAll(final int pageNumber, final int pageSize) {
        try {
            return repository.getAll(pageNumber, pageSize);
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }

    public Client findById(final String id) {
        try {
            return repository.findById(id);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }

    public Client switchStatus(final String id, boolean status) {
        try {
            Client client = repository.findById(id);
            return repository.save(new Client(
                    id,
                    client.name(),
                    client.birth(),
                    client.address(),
                    status ? Status.ATIVO : Status.INATIVO
            ));
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }

    public Client delete(final String id) {
        try {
            Client client = repository.findById(id);
            repository.delete(id);
            return client;
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }
}