package com.vekrest.vekclient.repository.client;

import com.vekrest.vekclient.repository.orm.ClientOrm;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ClientRepositoryWithMongo extends MongoRepository<ClientOrm, String> {
    @Query("{ 'status': 'ATIVO' }")
    Page<ClientOrm> findAllByStatusAtivo(Pageable pageable);
}
