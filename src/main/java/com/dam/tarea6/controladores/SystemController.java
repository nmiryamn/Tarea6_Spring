package com.dam.tarea6.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class SystemController {
	
	
	//Capta cualquier solicitud
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	//Redirecciona al controlador de gestión de actores
	@GetMapping("/actorsView")
	public String redirectToActorDealershipController() {
		return "redirect:showActorsView";
	}
	
	//Redirecciona a la plantilla de búsqueda
	@GetMapping("/searchActorByView")
	public String redirectToAtorSearchByTemplate() {
		return "searchActorBy";
	}
	
	//Redirecciona a la plantilla de insercción
	@GetMapping("/newActorView")
	public String redirectToNewActorTemplate() {
		return "newActor";
	}
	
	//---------------------------------------------------------
	
	//Redirecciona al controlador de gestión de películas
	@GetMapping("/peliculasView")
	public String redirectToPeliculaDealershipController() {
		return "redirect:showPeliculasView";
	}
		
		//Redirecciona a la plantilla de búsqueda
		@GetMapping("/searchPeliculaByView")
		public String redirectToPeliculaSearchByTemplate() {
			return "searchPeliculaBy";
		}
		
		//Redirecciona a la plantilla de insercción
		@GetMapping("/newPeliculaView")
		public String redirectToNewPeliculaTemplate() {
			return "newPelicula";
		}
		
	//----------------------------------------------------------
		
		@GetMapping("/actorPeliculasView")
		public String redirectToActorPeliculaDealershipController() {
			return "redirect:showActorPeliculasView";
		}
		
		//Redirecciona a la plantilla de insercción
		@GetMapping("/newActorPeliculaView")
		public String redirectToNewActorPelicula() {
			return "redirect:actAddLists";
		}
		
		//Redirecciona a la plantilla de insercción
		@GetMapping("/showPeliculasByActorView")
		public String redirectToSearchByActor() {
			return "redirect:actAddActorLists";
		}
		
		//Redirecciona a la plantilla de insercción
		@GetMapping("/showActorsByPeliculaView")
		public String redirectToSearchByPelicula() {
			return "redirect:actAddPeliculaLists";
		}
	


}
