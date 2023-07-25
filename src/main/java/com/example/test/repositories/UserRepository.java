package com.example.test.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.test.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query( "SELECT u from User u WHERE u.username = :username and u.password = :password")
    Optional<User> findByToken( String username, String password );
    
}
