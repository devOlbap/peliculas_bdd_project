package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRep;

    @Override
    public List<Pelicula> getPeliculas(){
        return peliculaRep.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id){
        return peliculaRep.findById(id);
    }
    
}
