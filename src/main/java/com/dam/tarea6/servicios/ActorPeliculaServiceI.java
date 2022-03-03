package com.dam.tarea6.servicios;

import java.util.List;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorPelicula;
import com.dam.tarea6.entidades.Pelicula;


public interface ActorPeliculaServiceI {

	public List<ActorPelicula> obtenerActorPeliculaPorIdActor(final long idActor);
	
	public List<ActorPelicula> obtenerActorPeliculaPorIdPelicula(final long idPelicula);
	
	public List<ActorPelicula> obtenerActorPeliculaTodos();
	
	public List<ActorPelicula> obtenerTodos();

	public void eliminarActorPeliculaPorId(final long id);

	public void anadirActorPelicula(final ActorPelicula actorPelicula);

	public void actualizarActorPelicula(final ActorPelicula actorPelicula);
}
