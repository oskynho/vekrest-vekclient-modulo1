package com.vekrest.vekclient.repository.client;

import com.vekrest.vekclient.repository.orm.ClientOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryWithMongo extends MongoRepository<ClientOrm, String> {

}
