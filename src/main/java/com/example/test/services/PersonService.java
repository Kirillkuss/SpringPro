package com.example.test.services;

import com.example.test.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test.repositories.PersonRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    @PersistenceContext
    private EntityManager em;

    public List<Person> findAllTwo(){
        return em.createQuery( "select e from Person e ").getResultList();
    }

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person getPersonById( Long id ){
        return personRepository.findById( id )
                               .orElseThrow( () -> new NoSuchElementException( "Клиента с таким ИД не существует" ));
    }

    public Person savePerson( Person person ) throws Exception{
        if( personRepository.findById( person.getId() ).isPresent() ) throw new IllegalArgumentException("Клинет с таким ИД уже существует");
        return personRepository.save( person );
    }

    public Person updatePerson( Person person ){
        if( personRepository.findById( person.getId() ).isEmpty() ) throw new IllegalArgumentException("Клинет с таким ИД не существует");
        return  personRepository.save( person);
    }

    public void deletePerson( Long id ){
        if( personRepository.findById( id ).isEmpty() ) throw new IllegalArgumentException("Клинет с таким ИД не существует");
        personRepository.deleteById( id );
    }

}
