package com.dam.tarea6.entidades;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase auxiliar de la entidad Pelicula
 * @author Usuario
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class PeliculaModelo {

	private Long id;
	
	@NotEmpty(message = "No puede estar vacío el título")
	@Column(length = 100)
	@Size(min = 3, max = 100, message = "El tamaño del título debe estar entre 3 y 100 caracteres")
	private String title;
	
	@Range(min=1888, max=2022, message="No es un año válido")
	private Integer year;

	@NotNull(message = "No puede estar vacía la duración")
	//@Pattern(regexp = "([0-9]+)h([0-9]+)m", message = "Formato de la duración de la película incorrecto")
	private String duration;

	@Size(min = 10, max = 1000, message = "El tamaño del resumen debe estar entre 10 y 255 caracteres")
	@Column(length = 1000)
	private String summary;
}
