package com.example.gasworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GasworksBatchApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GasworksBatchApplication.class, args);
        int exitCode = SpringApplication.exit(context);
        System.exit(exitCode);
    }
}