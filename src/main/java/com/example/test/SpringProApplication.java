package com.example.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API for Animal and Person CRUD", version = "3.0", description = "CRUD"))
public class SpringProApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringProApplication.class, args);
        System.out.println( "Success start ");
    }
}
