package com.dam.tarea6.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorModelo {

	private Long id;
	
	/** Nombre del actor/actriz */
	private String name;
	
	/** Apellido del actor/actriz */
	private String surname;
	
	/** Fecha de nacimiento del actor/actriz */
	private String birthdate;
	
	/** Nacionalidad del actor/actriz */
	private String nationality;
}
