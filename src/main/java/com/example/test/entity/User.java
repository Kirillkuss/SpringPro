package com.example.test.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 3317686311392412458L;

    @Schema( name        = "username",
             description = "admin",
             example     = "admin",
             required    = true )
    @NotNull         
    private String username;
    @Schema( name        = "password",
             description = "admin",
             example     = "admin",
             required    = true )
    @NotNull 
    private String password;
    @Hidden
    private String role;
    @Hidden
    private String email;

}