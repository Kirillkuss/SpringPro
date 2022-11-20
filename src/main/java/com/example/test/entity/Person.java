package com.example.test.entity;

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
    private Long id;
    @NotNull
    @Column( name = "name")
    private String name;
    @Column( name = "login")
    private String login;
    @Size( max = 13 )
    @Column( name = "phone" )
    private String phone;
    @Column( name = "wallet")
    private BigDecimal wallet;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "person_animal", joinColumns = @JoinColumn(name = "idPerson"),
            inverseJoinColumns = @JoinColumn(name = "idAnimal"))
    private List<Animal> animals;

    public Person(Long id, String name, String login, String phone, BigDecimal wallet, List<Animal> animals) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.phone = phone;
        this.wallet = wallet;
        this.animals = animals;
    }

    public Person(Long id, String name, String login, String phone, BigDecimal wallet) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.phone = phone;
        this.wallet = wallet;
    }
}
