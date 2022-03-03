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


@Controller
public class ActorPeliculaController {

	@Autowired
	private ActorPeliculaServiceI actorPeliculaServiceI;

	@Autowired
	private ActorServiceI actorServiceI;

	@Autowired
	private PeliculaServiceI peliculaServiceI;

	//private List<Equipo> listaEquipos = null;

	//private List<Futbolista> listaJugadores = null;

	/*@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}*/

	//BUSCAR PELICULAS POR ACTOR
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
	
	//BUSCAR ACTOR POR PELÍCULAS
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
					actorPeliculaModelo.setActor(String.valueOf(actor.getActor().getId()));
					actorPeliculaModelo.setPelicula(String.valueOf(actor.getPelicula().getId()));
					ActorPeliculaModeloList.add(actorPeliculaModelo);
				}
			}
		

		model.addAttribute("actorPeliculasListView", ActorPeliculaModeloList);
		model.addAttribute("btnDropActorEnabled", Boolean.FALSE);

		return "showActorPeliculas";
	}
	
	@GetMapping("/actAddLists")
	private String anadirLista(Model model) {

		final List<Actor> actores = actorServiceI.obtenerTodos();
		final List<Pelicula> peliculas = peliculaServiceI.obtenerTodasPeliculas();

		// Carga de datos al modelo
		model.addAttribute("actorListView", actores);
		model.addAttribute("peliculaListView", peliculas);

		return "newActorPelicula";
	}
	
	@GetMapping("/actAddActorLists")
	private String anadirListaActor(Model model) {

		final List<Actor> actores = actorServiceI.obtenerTodos();

		model.addAttribute("actorListView", actores);

		return "searchPeliculasByActor";
	}
	
	@GetMapping("/actAddPeliculaLists")
	private String anadirListaPelicula(Model model) {

		final List<Pelicula> peliculas = peliculaServiceI.obtenerTodasPeliculas();

		model.addAttribute("peliculaListView", peliculas);

		return "searchActorsByPelicula";
	}

	@PostMapping("/actAddActorPelicula")
	private String anadirActorPelicula(@Valid @ModelAttribute ActorPeliculaModelo newActorPelicula, BindingResult result)
			throws Exception {

		Pelicula p = peliculaServiceI.obtenerPeliculaPorId(Long.valueOf(newActorPelicula.getActor()));
		Actor a = actorServiceI.obtenerActorPorId(Long.valueOf(newActorPelicula.getPelicula()));

		ActorPelicula ap = new ActorPelicula();
		ap.setActor(a);
		ap.setPelicula(p);

		if (result.hasErrors()) {
			throw new Exception("Parámetros de matriculación erróneos");
		} else {
			actorPeliculaServiceI.anadirActorPelicula(ap);

		}

		return "redirect:index";
	}
	
	@PostMapping("/actDropActorPelicula")
	public String eliminarPeliculas(@RequestParam String actorPeliculaId, Model model) {

		// Eliminación de actor
		actorPeliculaServiceI.eliminarActorPeliculaPorId(Long.valueOf(actorPeliculaId));

		return "redirect:showActorPeliculasView";

	}
}
