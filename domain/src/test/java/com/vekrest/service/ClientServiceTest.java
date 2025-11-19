package com.vekrest.service;

import com.vekrest.entity.*;
import com.vekrest.exception.NotFoundException;
import com.vekrest.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientRepository repository;
    private ClientService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ClientRepository.class);
        service = new ClientService(repository);
    }

    @Test
    void registerShouldSaveNewClientWhenNotFound() {
        Client client = new Client("1", "John Doe", null, null, Status.ATIVO);
        when(repository.findById(client.id())).thenThrow(new NotFoundException());
        when(repository.save(any(Client.class))).thenReturn(client);

        Client result = service.register(client);

        assertEquals(client, result);
        verify(repository).save(client);
    }

    @Test
    void registerShouldUpdateExistingClient() {
        Client existingClient = new Client("1", "John Doe", null, null, Status.ATIVO);
        Client newClient = new Client("1", "Jane Doe", null, null, Status.ATIVO);
        when(repository.findById(existingClient.id())).thenReturn(existingClient);
        when(repository.save(any(Client.class))).thenReturn(newClient);

        Client result = service.register(newClient);

        assertEquals(newClient, result);
        verify(repository).save(newClient);
    }

    @Test
    void updateShouldModifyExistingClient() {
        Client existingClient = new Client("1", "John Doe", null, null, Status.ATIVO);
        Client updatedClient = new Client("1", "Jane Doe", null, null, Status.ATIVO);
        when(repository.findById(existingClient.id())).thenReturn(existingClient);
        when(repository.save(any(Client.class))).thenReturn(updatedClient);

        Client result = service.update("1", updatedClient);

        assertEquals(updatedClient, result);
        verify(repository).save(updatedClient);
    }

    @Test
    void findByIdShouldReturnClientWhenFound() {
        Client client = new Client("1", "John Doe", null, null, Status.ATIVO);
        when(repository.findById(client.id())).thenReturn(client);

        Client result = service.findById("1");

        assertEquals(client, result);
    }

    @Test
    void findByIdShouldThrowNotFoundExceptionWhenNotFound() {
        when(repository.findById("1")).thenThrow(new NotFoundException());

        assertThrows(NotFoundException.class, () -> service.findById("1"));
    }

    @Test
    void switchStatusShouldUpdateClientStatus() {
        Client client = new Client("1", "John Doe", null, null, Status.ATIVO);
        Client updatedClient = new Client("1", "John Doe", null, null, Status.INATIVO);
        when(repository.findById(client.id())).thenReturn(client);
        when(repository.save(any(Client.class))).thenReturn(updatedClient);

        Client result = service.switchStatus("1", false);

        assertEquals(Status.INATIVO, result.status());
        verify(repository).save(updatedClient);
    }

    @Test
    void deleteShouldRemoveClientAndReturnIt() {
        Client client = new Client("1", "John Doe", null, null, Status.ATIVO);
        when(repository.findById(client.id())).thenReturn(client);

        Client result = service.delete("1");

        assertEquals(client, result);
        verify(repository).delete("1");
    }

    @Test
    void getAllShouldReturnPaginationOfClients() {
        Pagination<Client> pagination = new Pagination<>(
                List.of(
                        new Client(
                                "1",
                                "John Doe",
                                LocalDate.of(2025, 1, 1),
                                new Address("00000-000", State.SP),
                                Status.ATIVO
                        )
                ), 0, 10, 1, 1
        );
        when(repository.getAll(1, 10)).thenReturn(pagination);

        Pagination<Client> result = service.getAll(1, 10);

        assertEquals(pagination, result);
    }
}
