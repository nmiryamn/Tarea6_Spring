package com.dam.tarea6.entidades;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PeliculaModelo {

	private Long id;
	
	private String title;
	
	private Integer year;

	private String duration;

	private String summary;
}
