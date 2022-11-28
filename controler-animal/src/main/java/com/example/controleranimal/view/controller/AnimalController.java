package com.example.controleranimal.view.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controleranimal.compartilhado.AnimalDto;

import com.example.controleranimal.service.AnimalService;
import com.example.controleranimal.view.model.AnimalModeloInclusao;
import com.example.controleranimal.view.model.AnimalModeloResponse;



@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping(value = "/status")
    public String statusServico(@Value("${local.server.port}") String porta){
        return String.format("Serviço ativo e executado na porta %s", porta);
    }

    @PostMapping
    public ResponseEntity<AnimalModeloResponse> criarAnimal(@RequestBody AnimalModeloInclusao animal){
        ModelMapper mapper = new ModelMapper();
        AnimalDto animalDto = mapper.map(animal, AnimalDto.class);
        animalDto = animalService.criarAnimal(animalDto);

        return new ResponseEntity<>(mapper.map(animalDto, AnimalModeloResponse.class),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnimalModeloResponse>> obterTodos(){
        List<AnimalDto> dtos = animalService.obterTodos();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<AnimalModeloResponse> resp = dtos.stream()
        .map(dto -> mapper.map(dto, AnimalModeloResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value="/{dono}/lista")
    public ResponseEntity<List<AnimalModeloResponse>> obterPorDono(@PathVariable Integer dono) {

        List<AnimalDto> dtos = animalService.obterPorDono(dono);

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<AnimalModeloResponse> resp = dtos.stream()
        .map(dto -> mapper.map(dto, AnimalModeloResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalModeloResponse> obterPorId(@PathVariable Integer id){

        Optional<AnimalDto> animal = animalService.obterPorId(id);

        if(animal.isPresent()){
            return new ResponseEntity<>(
                new ModelMapper().map(animal.get(), AnimalModeloResponse.class),
                HttpStatus.OK
            );

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    
    
}
