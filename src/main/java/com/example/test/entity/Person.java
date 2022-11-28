package com.example.test.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    @Schema( name = "id",
             description = "ИД пользователя",
             example = "1",
             required = true )       private Long id;

    @NotNull
    @Column( name        = "name")
    @Schema( name        = "name",
             description = "Имя",
             example     = "Кирилл",
             required    = true )    private String name;

    @Column( name = "login")
    @Schema( name        = "login",
             description = "Логин",
             example     = "Mouse711",
             required    = true )    private String login;

    @Size( max = 13 )
    @Column( name = "phone" )
    @Schema( name        = "phone",
            description = "Номер телефона",
            example     = "+375297844532",
            required    = true )      private String phone;

    @Column( name = "wallet")
    @Schema( name        = "wallet",
            description = "Кошелек",
            example     = "50000",
            required    = true )      private BigDecimal wallet;

    
    public Person(Long id, String name, String login, String phone, BigDecimal wallet) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.phone = phone;
        this.wallet = wallet;
    }
}
