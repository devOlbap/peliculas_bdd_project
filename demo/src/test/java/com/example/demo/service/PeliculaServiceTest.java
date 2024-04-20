package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @InjectMocks
    private PeliculaServiceImpl peliculaServiceImpl;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void guardarPeliculaTest(){
        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo("Mi pobre angelito 2");
        pelicula.setDirector("Ronaldo Jara");
        pelicula.setAnio(2023);
        pelicula.setGenero("Todo espectador");
        pelicula.setSinopsis("de un niño que se pierde en navidad..pero 2.");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        Pelicula peli_res = peliculaServiceImpl.createPelicula(pelicula);

        assertEquals("Mi pobre angelito 2", peli_res.getTitulo());

    }


}
