package com.example.controleranimal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controleranimal.compartilhado.AnimalDto;
import com.example.controleranimal.model.Animal;
import com.example.controleranimal.repository.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService{

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public AnimalDto criarAnimal(AnimalDto animal) {
        
        return salvarAnimal(animal);
    }

    @Override
    public List<AnimalDto> obterTodos() {

        List<Animal> animals = animalRepository.findAll();

        return animals.stream()
        .map(animal -> new ModelMapper().map(animal, AnimalDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<AnimalDto> obterPorId(Integer id) {
        
        Optional<Animal> animal = animalRepository.findById(id);

        if(animal.isPresent()){
            return Optional.of(new ModelMapper().map(animal.get(), AnimalDto.class));

        }
        
        return Optional.empty();
    }

    @Override
    public List<AnimalDto> obterPorDono(Integer dono) {
        
        List<Animal> animais = animalRepository.findByDono(dono);

        return animais.stream()
        .map(animal -> new ModelMapper().map(animal, AnimalDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public void removerAnimal(Integer id) {
        animalRepository.deleteById(id);
        
    }

    @Override
    public boolean definirComoMorto(Integer id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isPresent()){
            animal.get().setVivo(false);
            animalRepository.save(animal.get());
            return true;

        }
        return false;
    }

    @Override
    public AnimalDto atualizarAnimal(Integer id, AnimalDto animal) {
        animal.setId(id);

        return salvarAnimal(animal);
    }

    private AnimalDto salvarAnimal(AnimalDto animal){

        ModelMapper mapper = new ModelMapper();
        Animal animalEntidade = mapper.map(animal, Animal.class);
        animalEntidade = animalRepository.save(animalEntidade);

        return mapper.map(animalEntidade, AnimalDto.class);
    }

    
    
}
