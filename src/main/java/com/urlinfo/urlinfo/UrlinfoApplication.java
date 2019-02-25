package com.urlinfo.urlinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class UrlinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlinfoApplication.class, args);
	}

}
