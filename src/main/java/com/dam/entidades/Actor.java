package com.dam.entidades;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "actores")
public class Actor implements Serializable{

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 1L;

	/** Identificador de actores (PK) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** Nombre del actor/actriz */
	//@NotEmpty(message = "No puede estar vacío el nombre")
	//@Size(min = 3, max = 20, message = "El tamaño del nombre debe estar entre 3 y 20 caracteres")
	//@Column(name = "Nombre", nullable = false)
	private String name;
	
	/** Apellido del actor/actriz */
	//@NotEmpty(message = "No puede estar vacío el apellido")
	//@Size(min = 5, max = 50, message = "El tamaño del apellido debe estar entre 5 y 50 caracteres")
	//@Column(name = "Apellidos", nullable = false)
	private String surname;
	
	/** Fecha de nacimiento del actor/actriz */
	//@NotNull(message = "No puede estar vacía la fecha")
	//@Column(name = "FechaNac") 
	@Temporal(TemporalType.DATE)
	private Date birth_date;
	
	/** Nacionalidad del actor/actriz */
	//@NotEmpty(message = "No puede estar vacía la nacionalidad")
	//@Size(min = 5, max = 50, message = "El tamaño del apellido debe estar entre 3 y 20 caracteres")
	//@Column(name = "Nacionalidad", nullable = false)
	private String nationality;
	
	@ManyToMany(mappedBy = "actors")
    public List<Pelicula> movies = new ArrayList<>();
	
}
