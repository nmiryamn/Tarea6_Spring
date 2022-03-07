package com.dam.tarea6.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.exception.InvalidDataException;
import com.dam.tarea6.servicios.ActorServiceI;

/**
 * 
 * @author Usuario
 *
 */
@Controller
public class ActorController {

	/**
	 * Objeto de mi servicio de Actor
	 */
	@Autowired
	private ActorServiceI actorServiceI;
	
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}

	/**
	 * Método que mostrará todos los actores cuando lo llamemos desde el index. 
	 * Usaremos nuestra entidad modelo. 
	 * @param model Objeto Model
	 * @return showActors. Retorna la vista html que muestra los actores.
	 */
	@GetMapping("/showActorsView")
	public String mostrarActores(Model model) {

		// Obtención de actores
		final List<Actor> ActorList = actorServiceI.obtenerTodos();

		ActorModelo actorModelo;
		final List<ActorModelo> ActorModeloList = new ArrayList<>();
		
		if (!CollectionUtils.isEmpty(ActorList)) {
			for (Actor actor : ActorList) {
				actorModelo = new ActorModelo();
				actorModelo.setId(actor.getId());
				actorModelo.setName(actor.getName());
				actorModelo.setSurname(actor.getSurname());
				actorModelo.setNationality(actor.getNationality());
				actorModelo.setBirthdate(actor.getBirthdate());
				ActorModeloList.add(actorModelo);
			}
		}
		
		// Carga de datos al modelo
		model.addAttribute("actorsListView", ActorModeloList);
		model.addAttribute("btnDropActorEnabled", Boolean.FALSE);

		return "showActors";
	}

	/**
	 * Método que eliminará el actor que tenga el id que le pasamos por parámetro. 
	 * @param actorId Id del actor
	 * @param model Objeto Model
	 * @return "redirect:showActorsView" Redirecciona a showActorsView  
	 */
	@PostMapping("/actDropActor")
	public String eliminarActor(@RequestParam String actorId, Model model) {

		// Eliminación de actor
		actorServiceI.eliminarActor(Long.valueOf(actorId));

		return "redirect:showActorsView";

	}

	/**
	 * Método que busca actores por nombre, nacionalidad o apellido. 
	 * @param searchedActor 
	 * @param model Objeto Model
	 * @return "showActors" La vista que lista todos los actores
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actSearchActor")
	public String submitBuscarActorForm(@ModelAttribute Actor searchedActor, Model model) throws Exception {

		List<Actor> listaActores = new ArrayList<Actor>();
		
		System.out.println(searchedActor.getName());

		final String actorNombre = searchedActor.getName().toUpperCase();
		final String actorNacionalidad = searchedActor.getNationality().toUpperCase();
		final String actorApellido = searchedActor.getSurname().toUpperCase();
		
		if (StringUtils.hasText(actorNombre) && StringUtils.hasText(actorApellido)) {

			// Búsqueda por nombre y apellidos
			final Actor actor = actorServiceI.obtenerActorPorNombreYAppelidos(actorNombre, actorApellido);

			if (actor != null) {
				listaActores.add(actor);
				
			}
			
		} else if (!StringUtils.hasText(actorNombre)
				&& (StringUtils.hasText(actorNacionalidad) && StringUtils.hasText(actorApellido))) {

			listaActores = actorServiceI.obtenerActorPorApellidoYNacionalidad(actorApellido, actorNacionalidad);
			
		} else if (!StringUtils.hasText(actorNombre)
				&& (StringUtils.hasText(actorNacionalidad) || StringUtils.hasText(actorApellido))) {

			// Búsqueda por apellido o nacionalidad
			listaActores = actorServiceI.obtenerActorPorApellidoONacionalidad(actorApellido, actorNacionalidad);

		} else if(StringUtils.hasText(actorNombre) && (!StringUtils.hasText(actorNacionalidad) || !StringUtils.hasText(actorApellido))) {
			
			listaActores = actorServiceI.obtenerActoresPorNombre(actorNombre);
			
		} else {
			throw new Exception("Parámetros de búsquieda erróneos.");
		}

		// Carga de datos al modelo
		model.addAttribute("actorsListView", listaActores);
		model.addAttribute("btnDropActorEnabled", Boolean.TRUE);

		return "showActors";

	}

	/**
	 * Método que añade un nuevo actor a la base de datos mediante el actor modelo que pasamos por parámetro.
	 * @param newActor Objeto ActorModelo que usaremos para añadir un nuevo actor.
	 * @param result Respuesta que indica si hay algún error al introducir los parámetros.
	 * @return "redirect:showActorsView" Redirecciona a showActorsView  
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actAddActor")
	private String aniadirActor(@Valid @ModelAttribute ActorModelo newActor, BindingResult result) throws Exception {
		
		if (result.hasErrors()) {			
			throw new InvalidDataException(result);
		} else {
			
			Actor a = new Actor();
			
			a.setName(newActor.getName());
			a.setSurname(newActor.getSurname());
			a.setNationality(newActor.getNationality());
			a.setBirthdate(newActor.getBirthdate());
			
			a.setActorPeliculas(null);
			actorServiceI.anadirActor(a);
		}

		return "redirect:showActorsView";
	}
	
	/**
	 * Método en el que a partir del id de actor pasado por parámetro obtenemos un actor
	 * y añadimos el atributo al modelo. 
	 * @param actorId Id del actor.
	 * @param model Objeto Model
	 * @return "updateActor" Retorna la vista de actualización de actor. 
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actSetActor")
	public String actualizaSeteaActor(@RequestParam String actorId, Model model) throws Exception {
		
		Actor a = actorServiceI.obtenerActorPorId(Long.valueOf(actorId));
	
		model.addAttribute("actor", a);
		
		return "updateActor";
	}
	
	/**
	 * Método que actualiza un actor que le pasemos por parámetro.
	 * @param newActor Objeto Actor
	 * @param result Respuesta que indica si hay algún error al introducir los parámetros.
	 * @return "redirect:showActorsView" Redirecciona a showActorsView
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actUpdateActor")
	public String actualizaActores(@Valid @ModelAttribute Actor newActor, BindingResult result) throws Exception {
		
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		} else {
			// Se añade la nueva película
			actorServiceI.actualizarActor(newActor);
		}

		return "redirect:showActorsView";
	}
	
}
