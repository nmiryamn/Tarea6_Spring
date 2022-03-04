package com.dam.tarea6.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase auxiliar de ActorPelicula
 * @author Usuario
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class ActorPeliculaModelo {
	
	private Long id;

    private String actor;

    private String pelicula;

}
