package com.example.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.test.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
    
}
