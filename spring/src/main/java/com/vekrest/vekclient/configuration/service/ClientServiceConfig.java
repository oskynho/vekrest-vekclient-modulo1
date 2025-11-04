package com.vekrest.vekclient.configuration.service;

import com.vekrest.repository.ClientRepository;
import com.vekrest.service.ClientService;
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