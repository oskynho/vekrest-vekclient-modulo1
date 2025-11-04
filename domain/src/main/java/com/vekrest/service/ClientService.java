package com.vekrest.service;

import com.vekrest.entity.Client;
import com.vekrest.entity.Status;
import com.vekrest.exception.InternalServerException;
import com.vekrest.exception.NotFoundException;
import com.vekrest.exception.UnprocessableEntityException;
import com.vekrest.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

public class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository repository;

    public ClientService(
            ClientRepository repository) {
        this.repository = repository;
    }

    public Client register(Client client) {
        try {
            Client updateClient = repository.findById(client.id());
            return save(updateClient.id(), client);
        } catch (NotFoundException ex) {
            return save(client);
        }
    }

    private Client save(Client client) {
        return save(client.id(), client);
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
        } catch (UnprocessableEntityException ex) {
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro na regra de negocio ao salvar: {} na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
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
            LOG.error("Erro ao ativar/desativar cliente: {} na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }
}