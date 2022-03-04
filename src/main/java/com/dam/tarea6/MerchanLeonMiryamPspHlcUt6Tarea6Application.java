package com.dam.tarea6;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.ActorPelicula;
import com.dam.tarea6.servicios.ActorServiceI;

@SpringBootApplication
public class MerchanLeonMiryamPspHlcUt6Tarea6Application implements CommandLineRunner {

	/** Servicio: Gestión de actores */
	@Autowired
	private ActorServiceI actorServiceI;

	public static void main(String[] args) {
		SpringApplication.run(MerchanLeonMiryamPspHlcUt6Tarea6Application.class, args);
	}

	/**
	 * 
	 */
	@Override
	public void run(String... args) throws Exception {

		System.out.println("------------");

		//Obtención e iteración de elementos.
		
		ActorModelo actorModelo = new ActorModelo();
				
		final List<Actor> ActorList = actorServiceI.obtenerTodos();
		if (!CollectionUtils.isEmpty(ActorList)) {
			for (Actor actor : ActorList) {
				actorModelo.setId(actor.getId());
				actorModelo.setName(actor.getName());
				actorModelo.setSurname(actor.getSurname());
				actorModelo.setNationality(actor.getNationality());
				actorModelo.setBirthdate(actor.getBirthdate());
				System.out.println(actorModelo.toString());
			}
		}

		System.out.println("------------");
		
				
////		 Obtención e iteración de elementos.
//		final Actor actorPorNombre =  actorServiceI.obtenerActorPorNombre("María");
//		System.out.println(actorPorNombre.toString());
//		
//		 System.out.println("------------");
//				
////		Eliminar coche por Id
//		actorServiceI.eliminarActor(actorPorNombre.getId());
				
		//Añadir un coche
//		Actor actor = new Actor();
//		actor.setName("María");
//		actor.setSurname("Peña");
//		actor.setNationality("Española");
//		actor.setBirthdate(new Date(1998-05-02));	
//		actorServiceI.anadirActor(actor);

	}

}
