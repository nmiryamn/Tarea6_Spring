package com.dam.entidades;

import java.io.Serializable;
import com.dam.entidades.Actor;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity 
@Table(name = "peliculas")
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
	//@NotEmpty(message = "No puede estar vacío el título")
	//@Size(min = 3, max = 100, message = "El tamaño del título debe estar entre 3 y 100 caracteres")
	//@Column(name = "Titulo", nullable = false)
	private String title;
	
	/** Año de la película */
	//@NotEmpty(message = "No puede estar vacío el año")
	//@Size(min = 4, max = 4, message = "Año incorrecto")
	//@Column(name = "Anyo", nullable = false)
	private int year;
	
	/** Duración de la película */
	//@NotNull(message = "No puede estar vacía la duración")
	//@Column(name = "Duracion") 
	private int duration;
	
	/** Resumen de la película */
	//@Size(min = 10, max = 255, message = "El tamaño del resumen debe estar entre 10 y 255 caracteres")
	//@Column(name = "Resumen", nullable = true)
	private String summary;
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "pelicula_actor",
			joinColumns = @JoinColumn(name = "pelicula_id"),
			inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	private List<Actor> actors = new ArrayList<>();
}
