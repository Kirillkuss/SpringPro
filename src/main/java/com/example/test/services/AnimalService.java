package com.example.test.services;

import com.example.test.entity.Animal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AnimalService {

    List<Animal> animals = new LinkedList<>(Arrays.asList(new Animal( 1L, "Dog",BigDecimal.valueOf(324), 34 ),
                                                          new Animal( 2L, "Cat", new BigDecimal(234), 45) ));

    public List<Animal> getAll() throws Exception{
        return animals;
    }

    public Animal getById( Long id ) throws Exception{
        return animals.stream().filter(s -> Objects.equals( s.getId(), id)).findFirst().orElse( null );
    }

    public boolean delAnimal( Long id) throws Exception{
        return animals.removeIf( s-> Objects.equals( s.getId(), id));
    }

    public boolean addAnimal(Animal animal) throws Exception{
        return animals.add( animal );
    }

    public boolean modyAnimal( Animal animal){
        Optional<Animal> an = animals.stream().filter( s ->Objects.equals( s.getId(), animal.getId() )).findAny();
        if( an.isPresent() ){
            an.get().setName( animal.getName() );
            an.get().setAmount( animal.getAmount() );
            an.get().setCount( animal.getCount() );
        }
        return an.isPresent();
    }

}
