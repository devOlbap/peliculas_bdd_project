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

        //DEBEMOS MOVER estas validaciones para la clase Pelicula y limpiar en controlador... 
        //HACER MAS NEGOCIO (manejo de info) EN SERVICIO O REPOSITORY(?)

        List<String> errores = new ArrayList();

        //EJEMPLO DE VALIDACIONES EN LA CLASE.
        //DE HECHO ESTO PUEDE SER SOLO UNA FUNCION
        if(pelicula.validarTitulo().trim().length() > 1){
            errores.add(pelicula.validarTitulo());
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
