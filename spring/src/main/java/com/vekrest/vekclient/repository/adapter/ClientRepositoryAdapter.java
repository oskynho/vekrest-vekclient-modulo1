package com.vekrest.vekclient.repository.adapter;

import com.vekrest.entity.Address;
import com.vekrest.entity.Client;
import com.vekrest.vekclient.repository.orm.AddressOrm;
import com.vekrest.vekclient.repository.orm.ClientOrm;

public class ClientRepositoryAdapter {
    private ClientRepositoryAdapter() {
    }

    public static ClientOrm cast(Client client) {
        return new ClientOrm(
                client.id(),
                client.name(),
                client.birth(),
                cast(client.address()),
                client.status()
        );
    }

    public static Client cast(ClientOrm clientOrm) {
        return new Client(
                clientOrm.id(),
                clientOrm.name(),
                clientOrm.birth(),
                cast(clientOrm.address()),
                clientOrm.status()
        );
    }

    public static AddressOrm cast(Address address){
        return new AddressOrm(
                address.cep(),
                address.state()
        );
    }

    public static Address cast(AddressOrm addressOrm){
        return new Address(
                addressOrm.cep(),
                addressOrm.state()
        );
    }
}