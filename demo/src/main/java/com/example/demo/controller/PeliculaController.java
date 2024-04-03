package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    
    @PostMapping("/add")
    public Pelicula createPeli(@RequestBody Pelicula pelicula) {
        return peliculaService.createPelicula(pelicula);
    }
    @PutMapping("/{id}")
    public Pelicula updatePeli(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        return peliculaService.updatePelicula(id, pelicula);
    }
 
    
    
}
