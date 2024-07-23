package com.example.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://127.0.0.1:8084/swagger-ui/index.html#/
@Slf4j
@SpringBootApplication
@OpenAPIDefinition( info = @Info( title = "API for Animal and Person CRUD", version = "3.0", description = "CRUD" ))
@SecurityScheme( name = "Bearer Authentication",
                 type = SecuritySchemeType.HTTP,
                 bearerFormat = "JWT",
                 scheme = "bearer" )
public class SpringProApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringProApplication.class, args);
        log.info( "SpringPro Success start ");

    }
    

}