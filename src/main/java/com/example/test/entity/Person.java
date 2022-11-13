package com.example.test.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private Long id;
    @Column( name = "name")
    private String name;
}
