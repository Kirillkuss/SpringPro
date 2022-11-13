package com.example.test.services;

import com.example.test.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person getPersonById( Long id ){
        return personRepository.findById( id ).get();
    }

    public void savePerson( Person person ){
        personRepository.save( person );
    }

    public void updatePerson( Person person ){
        if( personRepository.findById(person.getId()).isPresent() ){
            personRepository.save( person );
        } else personRepository.save( person);
    }

    public void deletePerson( Long id ){
        personRepository.deleteById( id );
    }

}
