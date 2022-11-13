package com.example.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
}
