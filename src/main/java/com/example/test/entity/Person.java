package com.example.test.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Person")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Person implements Serializable{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    @Schema( name = "id",
             description = "ИД пользователя",
             example = "1",
             required = true )
    private Long id;

    @NotNull
    @Column( name        = "name")
    @Schema( name        = "name",
             description = "Имя",
             example     = "Кирилл",
             required    = true )
    private String name;

    @Column( name = "login")
    @Schema( name        = "login",
             description = "Логин",
             example     = "Mouse711",
             required    = true )
    private String login;

    @Size( max = 13 )
    @Column( name = "phone" )
    @Schema( name        = "phone",
            description = "Номер телефона",
            example     = "+375297844532",
            required    = true )
    private String phone;

    @Column( name = "wallet")
    @Schema( name        = "wallet",
            description = "Кошелек",
            example     = "50000",
            required    = true )
    private BigDecimal wallet;
}
