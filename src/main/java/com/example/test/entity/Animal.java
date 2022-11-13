package com.example.test.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Animal {

    private Long id;
    private String name;
    private BigDecimal amount;
    private Integer count;

    public Animal(Long id, String name, BigDecimal amount, Integer count) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
    }
}
