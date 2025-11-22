package com.vekrest.vekclient.vekclient.controller;

import com.vekrest.vekclient.entity.Client;
import com.vekrest.vekclient.entity.Pagination;
import com.vekrest.vekclient.service.ClientService;
import com.vekrest.vekclient.vekclient.controller.adapter.ClientControllerAdapter;
import com.vekrest.vekclient.vekclient.controller.dto.request.ClientRequest;
import com.vekrest.vekclient.vekclient.controller.dto.request.ClientUpdateRequest;
import com.vekrest.vekclient.vekclient.controller.dto.response.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vekrest/vekclient")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client")
    public Pagination<ClientResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize
    ) {
        return ClientControllerAdapter.cast(service.getAll(pageNumber, pageSize));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/client")
    public ClientResponse register(@Valid @RequestBody ClientRequest request) {
        Client client = ClientControllerAdapter.cast(request);
        return ClientControllerAdapter.cast(service.register(client));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/client/{id}")
    public ClientResponse update(
            @PathVariable("id") String id,
            @Valid @RequestBody ClientUpdateRequest request
    ) {
        Client client = ClientControllerAdapter.cast(request);
        return ClientControllerAdapter.cast(service.update(id, client));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/{id}")
    @Cacheable(value = "frete-id-cache", key = "#id")
    public ClientResponse getById(@PathVariable("id") String id) {
        return ClientControllerAdapter.cast(service.findById(id));
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
        service.delete(id);
    }
}