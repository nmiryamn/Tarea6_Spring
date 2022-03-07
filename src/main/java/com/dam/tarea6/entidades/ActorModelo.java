package com.dam.tarea6.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase auxiliar de Actor
 * @author Usuario
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class ActorModelo {

	private Long id;
	
	/** Nombre del actor/actriz */
	@NotEmpty(message = "No puede estar vacío el nombre")
	@Size(min = 3, max = 20, message = "El tamaño del nombre debe estar entre 3 y 20 caracteres")
	private String name;

	/** Apellido del actor/actriz */
	@NotEmpty(message = "No puede estar vacío el apellido")
	@Size(min = 5, max = 50, message = "El tamaño del apellido debe estar entre 5 y 50 caracteres")
	private String surname;
	
	/** Fecha de nacimiento del actor/actriz */
	@NotNull(message = "No puede estar vacía la fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	
	/** Nacionalidad del actor/actriz */
	@NotEmpty(message = "No puede estar vacía la nacionalidad")
	@Size(min = 5, max = 50, message = "El tamaño del apellido debe estar entre 3 y 20 caracteres")
	private String nationality;
	
}
