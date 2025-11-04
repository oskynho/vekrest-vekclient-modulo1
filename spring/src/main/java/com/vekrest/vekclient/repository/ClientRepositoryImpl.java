package com.vekrest.vekclient.repository;

import com.vekrest.entity.Client;
import com.vekrest.exception.InternalServerException;
import com.vekrest.exception.NotFoundException;
import com.vekrest.repository.ClientRepository;
import com.vekrest.vekclient.repository.adapter.ClientRepositoryAdapter;
import com.vekrest.vekclient.repository.client.ClientRepositoryWithMongo;
import com.vekrest.vekclient.repository.orm.ClientOrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private static final Logger LOG = LoggerFactory.getLogger(ClientRepositoryImpl.class);

    private final ClientRepositoryWithMongo repository;

    public ClientRepositoryImpl(ClientRepositoryWithMongo repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> getAll() {
        try {
            List<ClientOrm> clientsOrm =  repository.findAll();

            return clientsOrm.stream().map(ClientRepositoryAdapter::cast).collect(Collectors.toList());
        } catch (Exception ex) {
            LOG.error("Erro ao recuperar clientes: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Client save(Client client) {
        try {
            ClientOrm orm = ClientRepositoryAdapter.cast(client);
            return ClientRepositoryAdapter.cast(repository.save(orm));
        } catch (Exception ex) {
            LOG.error("Erro ao salvar cliente: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Client findById(final String id) {
        try {
            Optional<ClientOrm> optional = repository.findById(id);
            if (optional.isEmpty()) {
                throw new NotFoundException("Cliente nao encontrado");
            }
            return ClientRepositoryAdapter.cast(
                    repository.save(optional.get()));
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao procurar cliente por id: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public void delete(final String id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            LOG.error("Erro ao deletar cliente: {} o erro aconteceu na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }
}
