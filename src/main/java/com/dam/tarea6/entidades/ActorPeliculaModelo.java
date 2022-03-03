package com.dam.tarea6.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorPeliculaModelo {
	
	private Long id;

    private String actor;

    private String pelicula;

}
