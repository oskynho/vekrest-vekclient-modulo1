package com.vekrest.vekclient.vekclient.configuration.service;

import com.vekrest.vekclient.repository.ClientRepository;
import com.vekrest.vekclient.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientServiceConfig {

    @Bean
    public ClientService clientService(
            ClientRepository repository
    ) {
        return new ClientService(repository);
    }
}