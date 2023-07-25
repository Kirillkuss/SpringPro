package com.example.test.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Hidden
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;

    @Column( name = "username")
    @Schema( name        = "username",
             description = "admin",
             example     = "admin",
             required    = true )
    @NotNull         
    private String username;

    @Column( name = "password_user")
    @Schema( name        = "password",
             description = "admin",
             example     = "admin",
             required    = true )
    @NotNull 
    private String password;

    @Column( name = "role_user")
    @Hidden
    private String role;

    @Column( name = "email")
    @Hidden
    private String email;

}