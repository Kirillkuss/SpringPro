package com.example.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//http://127.0.0.1:8080/swagger-ui/index.html#/
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
    
    @Bean
    public HttpTraceRepository httpTraceRepository(){
        return new InMemoryHttpTraceRepository();
    }
}