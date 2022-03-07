package com.dam.tarea6.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.dam.tarea6.entidades.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "Pelicula")
@Data @AllArgsConstructor @NoArgsConstructor
public class Pelicula implements Serializable{

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 1L;

	/** Identificador de película (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Título de la película */
	@NotEmpty(message = "No puede estar vacío el título")
	@Column(length = 100)
	@Size(min = 3, max = 100, message = "El tamaño del título debe estar entre 3 y 100 caracteres")
	private String title;
	
	/** Año de la película */
	@Range(min=1888, max=2022, message="No es un año válido")
	private Integer year;
	
	/** Duración de la película */
	@NotNull(message = "No puede estar vacía la duración")
	//@Pattern(regexp = "([0-9]+)h([0-9]+)m", message = "Formato de la duración de la película incorrecto")
	private String duration;
	
	/** Resumen de la película */
	@Size(min = 10, max = 1000, message = "El tamaño del resumen debe estar entre 10 y 255 caracteres")
	@Column(length = 1000)
	private String summary;
	
	@OneToMany(mappedBy = "pelicula", cascade = CascadeType.REMOVE)
	private List<ActorPelicula> actorPeliculas;

	
	
}
