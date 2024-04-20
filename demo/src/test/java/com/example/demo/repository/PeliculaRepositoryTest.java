package com.example.demo.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;


    @Test
    public void guardarPeliculaTest(){
        Pelicula nueva_pelicula = new Pelicula();
        nueva_pelicula.setTitulo("Mi pobre angelito");
        nueva_pelicula.setDirector("Walt disnei");
        nueva_pelicula.setAnio(2023);
        nueva_pelicula.setGenero("FAMILIA");
        nueva_pelicula.setSinopsis("de un niño que se pierde en navidad...");

        Pelicula res_pelicula = peliculaRepository.save(nueva_pelicula);

        assertNotNull(res_pelicula.getId());
        assertEquals("Mi pobre angelito", res_pelicula.getTitulo());

    }


}
