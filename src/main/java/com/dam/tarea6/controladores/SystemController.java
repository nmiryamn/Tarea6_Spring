package com.dam.tarea6.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class SystemController {
	
	
	/**
	 * Método que nos redirecciona a nuestro index
	 * @return "index"
	 */
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	/**
	 * Método que nos redirecciona a nuestro método que muestra los actores
	 * @return "redirect:showActorsView" Retorna la redirección a dicho método
	 */
	@GetMapping("/actorsView")
	public String redirectToActorDealershipController() {
		return "redirect:showActorsView";
	}
	
	/**
	 * Método que nos redirecciona a nuestra vista que busca los actores por diferentes parámetros
	 * @return "searchActorBy". Retorna la vista html.
	 */
	@GetMapping("/searchActorByView")
	public String redirectToAtorSearchByTemplate() {
		return "searchActorBy";
	}
	
	/**
	 * Método que nos redirecciona a la plantilla de añadir Actor
	 * @return "newActor" Retorna la vista que añade un actor.
	 */
	@GetMapping("/newActorView")
	public String redirectToNewActorTemplate() {
		return "newActor";
	}
	
	//---------------------------------------------------------
	
	/**
	 * Método que nos redirecciona al método que muestra la vista de películas
	 * @return "redirect:showPeliculasView" Retorna la redirección a dicho método
	 */
	@GetMapping("/peliculasView")
	public String redirectToPeliculaDealershipController() {
		return "redirect:showPeliculasView";
	}
		/**
		 * Método que nos redirecciona a la plantilla de búsqueda de películas
		 * @return "searchPeliculaBy" Retorna la vista con la que buscamos una película por distintos parámetros
		 */
		@GetMapping("/searchPeliculaByView")
		public String redirectToPeliculaSearchByTemplate() {
			return "searchPeliculaBy";
		}
		
		/**
		 * Método que nos redirecciona a la plantilla de inserción de películas
		 * @return "newPelicula" Retorna la vista con la que insertamos una nueva película
		 */
		@GetMapping("/newPeliculaView")
		public String redirectToNewPeliculaTemplate() {
			return "newPelicula";
		}
		
	//----------------------------------------------------------
		
		/**
		 * Método que nos redirecciona al método que muestra los objetos ActorPelicula
		 * @return "redirect:showActorPeliculasView" Retorna la redirección a dicho método
		 */
		@GetMapping("/actorPeliculasView")
		public String redirectToActorPeliculaDealershipController() {
			return "redirect:showActorPeliculasView";
		}
		
		/**
		 * Método que nos redirecciona al método que añade los atributos de listas al modelo
		 * para posteriormente usarlas al añadir un objeto ActorPelicula
		 * @return "redirect:actAddLists". Retorna la redirección a dicho método
		 */
		@GetMapping("/newActorPeliculaView")
		public String redirectToNewActorPelicula() {
			return "redirect:actAddLists";
		}
		
		/**
		 * Método que nos redirecciona al método que añade la lista de actores al modelo
		 * para posteriormente usarlas al buscar películas por actor
		 * @return "redirect:actAddActorLists". Retorna la redirección a dicho método.
		 */
		@GetMapping("/showPeliculasByActorView")
		public String redirectToSearchByActor() {
			return "redirect:actAddActorLists";
		}
		
		/**
		 * Método que nos redirecciona al método que añade la lista de películas al modelo
		 * para posteriormente usarlas al buscar actores por película
		 * @return "redirect:actAddPeliculaLists". Retorna la redirección a dicho método.
		 */
		@GetMapping("/showActorsByPeliculaView")
		public String redirectToSearchByPelicula() {
			return "redirect:actAddPeliculaLists";
		}
	


}
