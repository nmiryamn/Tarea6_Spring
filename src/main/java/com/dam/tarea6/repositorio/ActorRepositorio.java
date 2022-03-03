package com.dam.tarea6.repositorio;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.Pelicula;

@Repository
public interface ActorRepositorio extends JpaRepository<Actor,Long> {
	
	//public static final String QUERYALL="Select id, bithdate, name, nationality, surname FROM actores";
	
	Actor findByNameAndSurname(String nombre, String apellidos);
	
	List<Actor> findByName(String name);
	
	Actor findBySurname(String apellidos);
	
	List<Actor> findByNameOrSurname(final String nombre, final String apellidos);
	
	List <Actor> findBySurnameOrNationality(final String surname, final String nationality);
	
	List <Actor> findBySurnameAndNationality(final String surname, final String nationality);
	
	List<Actor> findByBirthdate(Date fechNacimiento);
	
	List<Actor> findByNationality(String nationality);
	
}
