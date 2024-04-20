package com.example.demo.model;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        name= "titulo"
    )
    private String titulo;

    @Column(
        nullable = false,
        name= "anio"
    )
    private int anio;

    @Column(
        nullable = false,
        name= "director"
    )
    private String director;

    @Column(
        nullable = false,
        name= "genero"
    )
    private String genero;

    @Column(
        nullable = false,
        name= "sinopsis"
    )
    private String sinopsis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


    /** MOVEMOS LAS VALIDACIONES PARA LA CLASE Y ASI LIMPIAR EL CONTROLLER */
    public String validarTitulo(){
        // Validar titulo
        if (this.getTitulo().trim().length() > 100 || this.getTitulo().trim().length() == 0) {
            String msje = this.getTitulo().trim().length() > 100 ? "El título de la película no puede tener más de 100 caracteres." : "Debe ingresar un titulo válido.";
            return msje;
        }
        return "";
    }

    



}


/*
 * // obtener anio actual.
        int anioActual = LocalDate.now().getYear();

        

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
 * 
 * 
 */