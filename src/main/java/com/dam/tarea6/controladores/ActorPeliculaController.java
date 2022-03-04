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
import org.springframework.web.bind.annotation.RequestParam;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.ActorModelo;
import com.dam.tarea6.entidades.ActorPelicula;
import com.dam.tarea6.entidades.ActorPeliculaModelo;
import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.entidades.PeliculaModelo;
import com.dam.tarea6.servicios.ActorPeliculaServiceI;
import com.dam.tarea6.servicios.ActorServiceI;
import com.dam.tarea6.servicios.PeliculaServiceI;

/**
 * 
 * @author Usuario
 *
 */
@Controller
public class ActorPeliculaController {

	/**
	 * Objeto de mi servicio de Actor Película
	 */
	@Autowired
	private ActorPeliculaServiceI actorPeliculaServiceI;

	/**
	 * Objeto de mi servicio de Actor 
	 */
	@Autowired
	private ActorServiceI actorServiceI;

	/**
	 * Objeto de mi servicio de Película 
	 */
	@Autowired
	private PeliculaServiceI peliculaServiceI;


	/*@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}*/

	/**
	 * Método que buscará las películas por el actor que pasemos por parámetro. 
	 * @param searchedActor Objeto Actor
	 * @param model Objeto Model
	 * @return showPeliculas. Retorna la vista html que muestra las películas.
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actSearchPeliculasByActor")
	public String submitBuscarPeliculasByActorForm(@ModelAttribute Actor searchedActor, Model model) throws Exception {
		
		List<ActorPelicula> list = new ArrayList<>();
		List<Pelicula> listPeliculas = new ArrayList<>();

		final long idActor = searchedActor.getId();

		Pelicula p;

		if (StringUtils.hasText(String.valueOf(idActor))) {
			list = actorPeliculaServiceI.obtenerActorPeliculaPorIdActor(idActor);

			for	(ActorPelicula d : list) {
				p = d.getPelicula();
				listPeliculas.add(p);
			}

		}
		model.addAttribute("peliculasListView", listPeliculas);

		return "showPeliculas";
	}
	
	/**
	 * Método que buscará los actores por la película que pasemos por parámetro. 
	 * @param searchedPelicula Objeto Película
	 * @param model Objeto Model
	 * @return showActors. Retorna la vista html que muestra los actores.
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
		@PostMapping("/actSearchActorsByPelicula")
		public String submitBuscarActorsByPeliculaForm(@ModelAttribute Pelicula searchedPelicula, Model model) throws Exception {
			
			System.out.println("asidf");
			
			List<ActorPelicula> list = new ArrayList<>();
			List<Actor> listActors = new ArrayList<>();

			final long idActor = searchedPelicula.getId();

			Actor a;

			if (StringUtils.hasText(String.valueOf(idActor))) {
				list = actorPeliculaServiceI.obtenerActorPeliculaPorIdPelicula(idActor);

				for	(ActorPelicula d : list) {
					a = d.getActor();
					listActors.add(a);
				}

			}
			model.addAttribute("actorsListView", listActors);

			return "showActors";
		}

	/**
	 * Método que mostrará todos los actores_pelicula cuando lo llamemos desde el index. 
	 * @param model Objeto Mode
	 * @return showActorPeliculas. Retorna la vista html que muestra los objetos ActorPelicula
	 * mi base de datos.
	 */
	@GetMapping("/showActorPeliculasView")
	public String mostrarActores(Model model) {
		
		// Obtención de actores
		final List<ActorPelicula> ActorPeliculaList = actorPeliculaServiceI.obtenerTodos();
		
		ActorPeliculaModelo actorPeliculaModelo;
		final List<ActorPeliculaModelo> ActorPeliculaModeloList = new ArrayList<>();
		
		if (!CollectionUtils.isEmpty(ActorPeliculaList)) {
			for (ActorPelicula actor : ActorPeliculaList) {					
					actorPeliculaModelo = new ActorPeliculaModelo();
					actorPeliculaModelo.setId(actor.getId());
					actorPeliculaModelo.setActor(String.valueOf(actor.getActor().getName()));
					actorPeliculaModelo.setPelicula(String.valueOf(actor.getPelicula().getTitle()));
					ActorPeliculaModeloList.add(actorPeliculaModelo);
				}
			}
		

		model.addAttribute("actorPeliculasListView", ActorPeliculaModeloList);
		model.addAttribute("actoresListView", actorServiceI.obtenerTodos());
		model.addAttribute("btnDropActorEnabled", Boolean.FALSE);

		return "showActorPeliculas";
	}
	
	/**
	 * Método que añade al modelo los atributos de las listas para
	 * después acceder a ellas desde la vista de "newActorPelicula"
	 * @param model Objeto Model
	 * @return "newActorPelicula" Retorna la vista de añadir un nuevo objeto ActorPelícula
	 */
	@GetMapping("/actAddLists")
	private String anadirLista(Model model) {

		final List<Actor> actores = actorServiceI.obtenerTodos();
		final List<Pelicula> peliculas = peliculaServiceI.obtenerTodasPeliculas();

		// Carga de datos al modelo
		model.addAttribute("actorListView", actores);
		model.addAttribute("peliculaListView", peliculas);

		return "newActorPelicula";
	}
	
	/**
	 * Método que añade al modelo los atributos de la lista de actores para
	 * después acceder a ellas desde la vista de "searchPeliculasByActor"
	 * @param model Objeto Model
	 * @return "searchPeliculasByActor" Retorna la vista de buscar películas por un actor en concreto
	 */
	@GetMapping("/actAddActorLists")
	private String anadirListaActor(Model model) {

		final List<Actor> actores = actorServiceI.obtenerTodos();

		model.addAttribute("actorListView", actores);

		return "searchPeliculasByActor";
	}
	
	/**
	 * Método que añade al modelo los atributos de la lista de películas para
	 * después acceder a ellas desde la vista de "searchActorsByPelicula"
	 * @param model Objeto Model
	 * @return "searchActorsByPelicula" Retorna la vista de buscar actores por una película en concreto
	 */
	@GetMapping("/actAddPeliculaLists")
	private String anadirListaPelicula(Model model) {

		final List<Pelicula> peliculas = peliculaServiceI.obtenerTodasPeliculas();

		model.addAttribute("peliculaListView", peliculas);

		return "searchActorsByPelicula";
	}

	/**
	 * Método que añade un objeto ActorPelicula a nuestra base de datos.
	 * @param newActorPelicula Objeto ActorPeliculaModelo
	 * @param result Respuesta que indica si hay algún error al introducir los parámetros.
	 * @return "redirect:index" Redirecciona a la vista de index,
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actAddActorPelicula")
	private String anadirActorPelicula(@Valid @ModelAttribute ActorPeliculaModelo newActorPelicula, BindingResult result)
			throws Exception {

		if (result.hasErrors()) {
			
			throw new Exception("Parámetros erróneos");
		} else {
			Pelicula p = peliculaServiceI.obtenerPeliculaPorId(Long.valueOf(newActorPelicula.getActor()));
			Actor a = actorServiceI.obtenerActorPorId(Long.valueOf(newActorPelicula.getPelicula()));

			ActorPelicula ap = new ActorPelicula();
			ap.setActor(a);
			ap.setPelicula(p);
			
			actorPeliculaServiceI.anadirActorPelicula(ap);

		}

		return "redirect:index";
	}
	
	/**
	 * Método que eliminará el ActorPelicula que tenga el id que le pasamos por parámetro. 
	 * @param actorPeliculaId Id del ActorPelicula
	 * @param model Objeto Model
	 * @return "redirect:showActorPeliculasView" Redirecciona a la vista que contiene
	 * la lista de objetos ActorPelicula de mi base de datos  
	 */
	@PostMapping("/actDropActorPelicula")
	public String eliminarPeliculas(@RequestParam String actorPeliculaId, Model model) {

		// Eliminación de actor
		actorPeliculaServiceI.eliminarActorPeliculaPorId(Long.valueOf(actorPeliculaId));

		return "redirect:showActorPeliculasView";

	}
}
