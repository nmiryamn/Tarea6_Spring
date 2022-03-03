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
import com.dam.tarea6.servicios.ActorServiceI;


@Controller
public class ActorController {

	@Autowired
	private ActorServiceI actorServiceI;
	
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}

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
				actorModelo.setBirthdate(String.valueOf(actor.getBirthdate()));
				ActorModeloList.add(actorModelo);
			}
		}
		
		// Carga de datos al modelo
		model.addAttribute("actorsListView", ActorModeloList);
		model.addAttribute("btnDropActorEnabled", Boolean.FALSE);

		return "showActors";
	}

	@PostMapping("/actDropActor")
	public String eliminarActor(@RequestParam String actorId, Model model) {

		// Eliminación de actor
		actorServiceI.eliminarActor(Long.valueOf(actorId));

		return "redirect:showActorsView";

	}

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

	@PostMapping("/actAddActor")
	private String aniadirActor(@Valid @ModelAttribute ActorModelo newActor, BindingResult result) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		
		Date date = formatter.parse(newActor.getBirthdate());
		
		Actor a = new Actor();
		
		a.setName(newActor.getName().toUpperCase());
		a.setSurname(newActor.getSurname().toUpperCase());
		a.setNationality(newActor.getNationality().toUpperCase());
		a.setBirthdate(date);
		

		if (result.hasErrors()) {
			throw new Exception("Parámetros de actor erróneos");
		} else {
			// Se añade el nuevo coche
			a.setActorPeliculas(null);
			actorServiceI.anadirActor(a);
		}

		return "redirect:showActorsView";
	}
	
	@PostMapping("/actSetActor")
	public String actualizaSeteaActor(@RequestParam String actorId, Model model) throws Exception {
		// Actualización de película
		
		Actor a = actorServiceI.obtenerActorPorId(Long.valueOf(actorId));
	
		model.addAttribute("actor", a);
		
		return "updateActor";
	}
	
	@PostMapping("/actUpdateActor")
	public String actualizaActores(@Valid @ModelAttribute Actor newActor, BindingResult result) throws Exception {
		// Actualización de película
		
		System.out.println(newActor.toString());
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros de actor erróneos");
		} else {
			// Se añade la nueva película
			actorServiceI.actualizarActor(newActor);
		}

		return "redirect:showActorsView";
	}
	
}
