package com.example.controleranimal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controleranimal.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{
    
}
