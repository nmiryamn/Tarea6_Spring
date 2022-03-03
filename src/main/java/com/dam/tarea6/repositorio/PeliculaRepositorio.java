package com.dam.tarea6.repositorio;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.tarea6.entidades.Pelicula;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long>{

	Pelicula findByTitle(String titulo);
	
	List<Pelicula> findByYear(Date annoEstreno);
	
	List<Pelicula> findByDuration(String minDuracion);
	
	List<Pelicula> findByTitleOrYear(final String title, final int year);
	
	Pelicula findByTitleAndYear(final String title, final int year);
	
}
