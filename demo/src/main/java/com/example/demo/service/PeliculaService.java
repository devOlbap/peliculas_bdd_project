package com.example.demo.service;


import com.example.demo.model.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> getPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);

    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long id, Pelicula pelicula);
}
