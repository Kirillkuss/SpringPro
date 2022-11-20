package com.example.test.entity;

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
    private Long id;
    @Column( name = "name")
    private String name;
    @Column( name = "amount")
    private BigDecimal amount;
    @Column( name = "count")
    private Integer count;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Person person;

    public Animal(Long id, String name, BigDecimal amount, Integer count, Person person) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
        this.person = person;
    }

    public Animal(Long id, String name, BigDecimal amount, Integer count) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
    }

}
