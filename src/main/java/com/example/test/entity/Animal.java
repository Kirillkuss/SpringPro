package com.example.test.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table( name= "Animal")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Animal implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    @Schema( name        = "id",
            description = "ИД питомца",
            example     = "1",
            required    = true )   private Long id;

    @Column( name = "name")
    @Schema( name        = "name",
            description = "Название питомца",
            example     = "Cat",
            required    = true )    private String name;

    @Column( name = "amount")
    @Schema( name        = "amount",
            description = "Стоимость",
            example     = "400",
            required    = true )    private BigDecimal amount;

    @Column( name = "count")
    @Schema( name        = "count",
            description = "Количество",
            example     = "30",
            required    = true )    private Integer count;

    public Animal(Long id, String name, BigDecimal amount, Integer count) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
    }

}
