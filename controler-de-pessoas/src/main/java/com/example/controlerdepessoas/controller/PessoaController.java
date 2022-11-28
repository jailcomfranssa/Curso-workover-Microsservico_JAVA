package com.example.controlerdepessoas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlerdepessoas.model.Pessoa;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

    @GetMapping()
    public List<Pessoa> listar(){

        Pessoa p1 = new Pessoa("ja", 20, "2338-5055");
        Pessoa p2 = new Pessoa("Maria", 30, "3238-9865");

        ArrayList<Pessoa> pessoas = new ArrayList<>();

        pessoas.add(p1);
        pessoas.add(p2);

        return pessoas;

    }
    
}
