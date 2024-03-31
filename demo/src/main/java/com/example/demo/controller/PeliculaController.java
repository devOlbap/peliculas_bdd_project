package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.PeliculaService;
import com.example.demo.model.Pelicula;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getPelis(){
        return peliculaService.getPeliculas();
    }
    @GetMapping("/{id}")
    public Optional<Pelicula> getPeliById(@PathVariable Long id) {
        return peliculaService.getPeliculaById(id);
    }
    
    
    
}
