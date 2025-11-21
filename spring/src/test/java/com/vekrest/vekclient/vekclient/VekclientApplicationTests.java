package com.vekrest.vekclient.vekclient;

import com.vekrest.vekclient.repository.ClientRepository;
import com.vekrest.vekclient.service.ClientService;
import com.vekrest.vekclient.vekclient.repository.client.ClientRepositoryWithMongo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class VekclientApplicationTests {
	@MockitoBean
	private MongoTemplate mongoTemplate;

	@MockitoBean
	private ClientService clientService;

	@MockitoBean
	private ClientRepository clientRepositoryInterface;

	@MockitoBean
	private LettuceConnectionFactory redisConnectionFactory;

	@MockitoBean
	private RedisCacheManager redisCacheManager;

	@MockitoBean
	private ClientRepositoryWithMongo clientRepositoryWithMongo;

	@Test
	void contextLoads() {
	}

}
