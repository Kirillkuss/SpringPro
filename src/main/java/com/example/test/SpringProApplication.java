package com.example.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.test.services.UserDetailsService;

//http://127.0.0.1:8090/swagger-ui/index.html#/
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API for Animal and Person CRUD", version = "3.0", description = "CRUD"))
public class SpringProApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringProApplication.class, args);
        System.out.println( "Success start ");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService( PasswordEncoder encoder ){
        List<UserDetails> userList = new ArrayList<>();
        userList.add( new User( "admin", encoder.encode( "admin"), Arrays.asList( new SimpleGrantedAuthority( "ROLE_USER" ))));
        userList.add( new User( "woody", encoder.encode("password"), Arrays.asList( new SimpleGrantedAuthority( "ROLE_USER" ))));
        return new InMemoryUserDetailsManager( userList ); 
    }

}
