package com.example.controleranimal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controleranimal.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

    List<Animal> findByDono(Integer dono);
    
}
