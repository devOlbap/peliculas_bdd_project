package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> createPeli(@RequestBody Pelicula pelicula) {
        //Lista de errores en validaciones
        List<String> errores = new ArrayList<>();

        // obtener anio actual.
        int anioActual = LocalDate.now().getYear();

        // Validar titulo
        if (pelicula.getTitulo().trim().length() > 100 || pelicula.getTitulo().trim().length() == 0) {
            String msje = pelicula.getTitulo().trim().length() > 100 ? "El título de la película no puede tener más de 100 caracteres." : "Debe ingresar un titulo válido.";
            errores.add(msje);
        }

        // Validar anio
        if(pelicula.getAnio()<1000 || pelicula.getAnio() > anioActual){
            String msje = pelicula.getAnio() > anioActual ? "El año no debe ser mayor al año actual." : "Debe ingresar un año válido.";
            errores.add(msje);         
        }

        // Validar director
        if (pelicula.getDirector().trim().length() > 100 || pelicula.getDirector().trim().length() ==0) {
            String msje = pelicula.getDirector().length() > 100 ? "El nombre del director no puede tener más de 100 caracteres." : "Debe ingresar un director válido.";
            errores.add(msje);
        }

        //validar genero.
        if (pelicula.getGenero().trim().length() > 50 || pelicula.getGenero().trim().length() ==0) {
            String msje = pelicula.getGenero().length() > 50 ? "El género no puede tener más de 50 caracteres." : "Debe ingresar un género válido.";
            errores.add(msje);
        }

        //validar sinopsis.
        if (pelicula.getSinopsis().trim().length() > 255 || pelicula.getSinopsis().trim().length() ==0) {
            String msje = pelicula.getSinopsis().length() > 255 ? "La sinópsis no puede tener más de 255 caracteres." : "Debe ingresar una sinópsis válida.";
            errores.add(msje);
        }

        // Si no hay errores, llamar al método del servicio para crear la película
        if (errores.isEmpty()) {
            return ResponseEntity.ok(peliculaService.createPelicula(pelicula));
        }
        
        // Si hay errores, devolver una respuesta con código de error 400 (BadRequest) y la lista de errores
        return ResponseEntity.badRequest().body(errores);

    }
    @PutMapping("/{id}")
    public Pelicula updatePeli(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        return peliculaService.updatePelicula(id, pelicula);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePeli(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        if(peliculaService.deletePelicula(id)){
            response.put("mensaje", "Película eliminada exitosamente con ID: " + id);
            return ResponseEntity.ok().body(response);
        }
        response.put("mensaje", "No existe registro con el ID:"+id);
        return ResponseEntity.badRequest().body(response);
    }
    
}
