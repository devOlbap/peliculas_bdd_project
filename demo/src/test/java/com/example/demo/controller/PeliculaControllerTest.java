package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;


@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    //@Autowired
    //private PeliculaService peliculaService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaService peliculaServiceMock;

    @Test
    public void obtenerPeliculasTest() throws Exception{

        Pelicula nueva_pelicula_1 = new Pelicula();
        nueva_pelicula_1.setId(1L);
        nueva_pelicula_1.setTitulo("Mi pobre angelito");
        nueva_pelicula_1.setDirector("Walt disnei");
        nueva_pelicula_1.setAnio(2021);
        nueva_pelicula_1.setGenero("FAMILIA");
        nueva_pelicula_1.setSinopsis("de un niño que se pierde en navidad...");

        Pelicula nueva_pelicula_2 = new Pelicula();
        nueva_pelicula_2.setId(2L);
        nueva_pelicula_2.setTitulo("Mi pobre angelito2");
        nueva_pelicula_2.setDirector("Walt disne 2");
        nueva_pelicula_2.setAnio(2022);
        nueva_pelicula_2.setGenero("FAMILIA 2");
        nueva_pelicula_2.setSinopsis("de un niño que se pierde en navidad... pero 2");

        List<Pelicula> peliculas_list = List.of(nueva_pelicula_1,nueva_pelicula_2);

        List<EntityModel<Pelicula>> peliculas_resources = peliculas_list.stream()
            .map(pelicula -> EntityModel.of(pelicula))
            .collect(Collectors.toList());

        when(peliculaServiceMock.getPeliculas()).thenReturn(peliculas_list);


        // peliculas_list = peliculas_resources.stream()
        //     .map(entityModel -> entityModel.getContent())
        //     .collect(Collectors.toList());

        // when(peliculaServiceMock.getPeliculas()).thenReturn(peliculas_list);


        mockMvc.perform(get("/peliculas"))
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$.[0].titulo").value("Mi pobre angelito"))
            .andExpect(jsonPath("$.[1].titulo").value("Mi pobre angelito2"))

        //    .andExpect(jsonPath("$._embedded.peliculas_list.length()").value(2))
        //    .andExpect(jsonPath("$._embedded.peliculas_list[0].titulo").value("Mi pobre angelito"))
        //    .andExpect(jsonPath("$._embedded.peliculas_list[1].titulo").value("Mi pobre angelito2"))
        ;
    }

    
    
}
