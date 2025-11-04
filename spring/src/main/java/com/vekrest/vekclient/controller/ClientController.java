package com.vekrest.vekclient.controller;

import com.vekrest.entity.Client;
import com.vekrest.repository.ClientRepository;
import com.vekrest.service.ClientService;
import com.vekrest.vekclient.controller.adapter.ClientControllerAdapter;
import com.vekrest.vekclient.controller.dto.request.ClientRequest;
import com.vekrest.vekclient.controller.dto.response.ClientListResponse;
import com.vekrest.vekclient.controller.dto.response.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vekrest")
public class ClientController {
    private final ClientRepository repository;
    private final ClientService service;

    public ClientController(ClientRepository repository, ClientService service) {
        this.repository = repository;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client")
    public ClientListResponse getAll() {
        return ClientControllerAdapter.cast(repository.getAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/client")
    public ClientResponse save(@Valid @RequestBody ClientRequest request) {
        Client client = ClientControllerAdapter.cast(request);
        return ClientControllerAdapter.cast(service.register(client));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/{id}")
    @Cacheable(value = "frete-cache", key = "#id")
    public ClientResponse getById(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(repository.findById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/activate/{id}")
    public ClientResponse activate(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(service.switchStatus(id, true));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/client/desactivate/{id}")
    public ClientResponse desactivate(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(service.switchStatus(id, false));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/client/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }
}