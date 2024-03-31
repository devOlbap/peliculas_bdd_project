package com.example.demo.repository;

import com.example.demo.model.Pelicula;

import org.springframework.data.jpa.repository.JpaRepository;


public interface  PeliculaRepository extends JpaRepository<Pelicula,Long>{
   
}

