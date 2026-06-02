package com.example.gasworks_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.gasworks_api")
public class GasworksApiApplication {

	public static void main(String[] args) {
		System.setProperty("spring.datasource.url", "jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1");
        System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
        System.setProperty("spring.datasource.username", "sa");
        System.setProperty("spring.datasource.password", "");
        System.setProperty("spring.sql.init.mode", "always");

		SpringApplication.run(GasworksApiApplication.class, args);
	}

}
