package com.dam.tarea6.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.repositorio.ActorRepositorio;

@Service
public class ActorServiceImpl implements ActorServiceI{

	@Autowired
	private ActorRepositorio actorRepositorio;

	@Override
	public void anadirActor(Actor actor) {
		actorRepositorio.save(actor);			
	}

	@Override
	public void eliminarActor(long IdActor) {
		actorRepositorio.deleteById(IdActor);
	}

	@Override
	public void actualizarActor(Actor actor) {
		actorRepositorio.save(actor);
	}


	@Override
	public List<Actor> obtenerTodos() {
		return actorRepositorio.findAll();
	}

	@Override
	public List<Actor> obtenerActorPorApellidoONacionalidad(String surname, String nacionalidad) {
		return actorRepositorio.findBySurnameOrNationality(surname, nacionalidad);
	}

	@Override
	public List<Actor> obtenerActorPorApellidoYNacionalidad(String surname, String nacionalidad) {
		return actorRepositorio.findBySurnameAndNationality(surname, nacionalidad);
	}

	@Override
	public Actor obtenerActorPorNombreYAppelidos(String nombre, String apellidos) {
		final Actor actor = actorRepositorio.findByNameAndSurname(nombre, apellidos);
		return actor;	
	}

	@Override
	public List<Actor> obtenerActoresPorNombre(String name) {
		return actorRepositorio.findByName(name);
	}

	@Override
	public Actor obtenerActorPorId(Long Id) {
		return actorRepositorio.getById(Id);
	}
	
	

}
