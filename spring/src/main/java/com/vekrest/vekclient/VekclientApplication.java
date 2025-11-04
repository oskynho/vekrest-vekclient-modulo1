package com.vekrest.vekclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VekclientApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(VekclientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VekclientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("VEKREST -> VEKCLIENT - INICIALIZADO COM SUCESSO!");
	}
}
