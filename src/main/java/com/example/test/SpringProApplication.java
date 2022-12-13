package com.example.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://127.0.0.1:8080/swagger-ui/index.html#/
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API for Animal and Person CRUD", version = "3.0", description = "CRUD"))
@SecurityScheme(name = "User name", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SpringProApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringProApplication.class, args);
        System.out.println( "Success start ");
    }

}
