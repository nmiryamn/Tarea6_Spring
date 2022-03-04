package com.dam.tarea6.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorPelicula;
import com.dam.tarea6.entidades.Pelicula;

@Repository
public interface ActorPeliculaRepositorio extends JpaRepository<ActorPelicula,Long>{

	/**
	 * Método del repositorio al que le pasamos una Query que nos devolverá los 
	 * objetos ActorPelicula que contengan el id del actor pasado por parámetro
	 * @param Id. El identificador del Actor
	 * @return 
	 */
	@Query(value = "SELECT * FROM actor_pelicula WHERE actor_id = ?", nativeQuery = true)
	List <ActorPelicula> findActorPeliculaByActorId(Long Id);
	
	/**
	 * Método del repositorio al que le pasamos una Query que nos devolverá los 
	 * objetos ActorPelicula que contengan el id de la película pasado por parámetro
	 * @param Id. El identificador de la Pelicula
	 * @return 
	 */
	@Query(value = "SELECT * FROM actor_pelicula WHERE pelicula_id = ?", nativeQuery = true)
	List <ActorPelicula> findActorPeliculaByPeliculaId(Long Id);

	/**
	 * Método del repositorio que nos devuelve los objetos ActorPelicula que se encuentran
	 * en la base de datos
	 * @return 
	 */
	@Query(value = "SELECT id, actor_id, pelicula_id FROM actor_pelicula", nativeQuery = true)
	List <ActorPelicula> findActorPelicula();

}
