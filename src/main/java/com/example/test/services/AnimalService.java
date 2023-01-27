package com.example.test.services;

import com.example.test.entity.Animal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;

@Service
public class AnimalService {

    @PersistenceContext
    EntityManager em;

    public List<Animal> getAll() throws Exception{
        return em.createQuery(" select e from Animal e").getResultList();
    }

    public Animal getById( Long id ) throws Exception{
        return ( Animal ) em.createQuery("select e from Animal e where e.id = ?1")
                            .setParameter(1 , id )
                            .getResultList()
                            .stream().findFirst().orElse( null );
    }

    @Transactional
    public void delAnimal( Long id) throws Exception{
        Animal animal = em.find( Animal.class, id );
        em.remove( animal );
    }

    @Transactional
    public void addAnimal(Animal animal) throws Exception{
        em.merge( animal );
    }

    @Transactional
    public void modyAnimal( Animal animal){
        Animal response = em.find( Animal.class, animal.getId() );
        response.setName( animal.getName() );
        response.setAmount( animal.getAmount() );
        response.setCount( animal.getCount() );
        em.merge( response );
    }

    public Integer getCount() throws Exception{
        Long response = (Long) em.createQuery( "select count ( t.id ) from Animal t")
                                 .getResultList()
                                 .stream().findFirst().orElse(0L);
        return response.intValue();
    }

}
