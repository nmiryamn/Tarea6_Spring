package com.dam.tarea6.servicios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.Pelicula;

public interface ActorServiceI {

	public List<Actor> obtenerTodos();
	
	public void anadirActor(final Actor actor);
	
	public void eliminarActor(final long IdActor);
	
	public void actualizarActor(final Actor actor);
	
	public Actor obtenerActorPorId(Long Id);
	
	public Actor obtenerActorPorNombreYAppelidos(String nombre, String apellidos);
	
	public List<Actor> obtenerActoresPorNombre(final String name);
	
	public List<Actor> obtenerActorPorApellidoONacionalidad(final String surname, final String nacionalidad);
	
	public List<Actor> obtenerActorPorApellidoYNacionalidad(final String surname, final String nacionalidad);
}
