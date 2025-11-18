package com.vekrest.vekclient.controller;

import com.vekrest.entity.Client;
import com.vekrest.entity.Pagination;
import com.vekrest.service.ClientService;
import com.vekrest.vekclient.controller.adapter.ClientControllerAdapter;
import com.vekrest.vekclient.controller.dto.request.ClientRequest;
import com.vekrest.vekclient.controller.dto.response.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vekrest")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/vekclient/client")
    public Pagination<Client> getAll(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize
    ) {
        return service.getAll(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/vekclient/client")
    public ClientResponse save(@Valid @RequestBody ClientRequest request) {
        Client client = ClientControllerAdapter.cast(request);
        return ClientControllerAdapter.cast(service.register(client));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/vekclient/client/{id}")
    @Cacheable(value = "frete-id-cache", key = "#id")
    public ClientResponse getById(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(service.findById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/vekclient/client/activate/{id}")
    public ClientResponse activate(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(service.switchStatus(id, true));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/vekclient/client/desactivate/{id}")
    public ClientResponse desactivate(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(service.switchStatus(id, false));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/vekclient/client/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}