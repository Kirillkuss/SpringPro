package com.example.test.services;

import com.example.test.entity.Animal;
import com.example.test.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AnimalService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAll() throws Exception{
        return em.createQuery(" select e from Animal e").getResultList();
    }

    public Animal getById( Long id ) throws Exception{
        return ( Animal ) em.createQuery("select e from Animal e where e.id = ?1")
                            .setParameter(1 , id )
                            .getResultList()
                            .stream().findFirst().orElseThrow();
    }

    @Transactional
    public void delAnimal( Long id) throws Exception{
        em.remove( animalRepository.findById( id )
                                   .orElseThrow( () ->new NoSuchElementException("Питомца с такми ИД не существует")) );
    }

    @Transactional
    public void addAnimal(Animal animal) throws Exception{
        if ( animalRepository.findById( animal.getId() ).isPresent()) throw new IllegalArgumentException( "Питомец с таким ИД уже существует, укажите другой");
        em.merge( animal );
    }

    @Transactional
    public void modyAnimal( Animal animal) throws Exception{
        if ( animalRepository.findById( animal.getId() ).isEmpty() ) throw new IllegalArgumentException( "Питомец с таким ИД не существует, укажите другой");
        em.merge( animal );
    }

    public Integer getCount() throws Exception{
        Long response = (Long) em.createQuery( "select count ( t.id ) from Animal t")
                                 .getResultList()
                                 .stream().findFirst().orElse(0L);
        return response.intValue();
    }

}
