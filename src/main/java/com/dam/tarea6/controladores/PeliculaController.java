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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.entidades.PeliculaModelo;
import com.dam.tarea6.exception.InvalidDataException;
import com.dam.tarea6.servicios.PeliculaServiceI;

/**
 * 
 * @author Usuario
 *
 */
@Controller
public class PeliculaController {

	/**
	 * Objeto de mi servicio de películas
	 */
	@Autowired
	private PeliculaServiceI peliculaServiceI;
	
	/*@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}*/

	/**
	 * Método que mostrará todas las películas cuando lo llamemos desde el index. 
	 * Usaremos nuestra entidad modelo. 
	 * @param model Objeto Model
	 * @return showPeliculas. Esta es la vista html que muestra las películas.
	 */
	@GetMapping("/showPeliculasView")
	public String mostrarPeliculas(Model model) {

		// Obtención de actores
		final List<Pelicula> peliculaList = peliculaServiceI.obtenerTodasPeliculas();

		PeliculaModelo peliculaModelo;
		final List<PeliculaModelo> peliculaModeloList = new ArrayList<>();
		
		if (!CollectionUtils.isEmpty(peliculaList)) {
			for (Pelicula pelicula : peliculaList) {
				peliculaModelo = new PeliculaModelo();
				peliculaModelo.setId(pelicula.getId());
				peliculaModelo.setTitle(pelicula.getTitle());
				peliculaModelo.setYear(pelicula.getYear());
				peliculaModelo.setDuration(pelicula.getDuration());
				peliculaModelo.setSummary(String.valueOf(pelicula.getSummary()));
				peliculaModeloList.add(peliculaModelo);
			}
		}
		
		// Carga de datos al modelo
		model.addAttribute("peliculasListView", peliculaModeloList);
		model.addAttribute("btnDropActorEnabled", Boolean.FALSE);

		return "showPeliculas";
	}

	/**
	 * Método que eliminará la película que tenga el id que le pasamos por parámetro. 
	 * @param peliculaId Id de la película
	 * @param model Objeto Model
	 * @return "redirect:showActorsView" Redirecciona a showActorsView  
	 */
	@PostMapping("/actDropPelicula")
	public String eliminarPeliculas(@RequestParam String peliculaId, Model model) {

		// Eliminación de actor
		peliculaServiceI.eliminarPelicula(Long.valueOf(peliculaId));

		return "redirect:showPeliculasView";

	}

	/**
	 * Método que busca películas por título o año. 
	 * @param searchedPelicula 
	 * @param model Objeto Model
	 * @return "showPeliculas" La vista que lista todas las películas
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actSearchPelicula")
	public String submitBuscarActorForm(@ModelAttribute Pelicula searchedPelicula, Model model) throws Exception {

		List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

		final String peliculaTitle = searchedPelicula.getTitle().toUpperCase();
		final Integer peliculaYear = searchedPelicula.getYear();
		
		System.out.println(peliculaTitle);
		
		if (StringUtils.hasText(peliculaTitle) && peliculaYear != null) {

			// Búsqueda por nombre y apellidos
			final Pelicula pelicula = peliculaServiceI.obtenerPeliculaPorTituloYAnyo(peliculaTitle, peliculaYear);

			if (pelicula != null) {
				listaPeliculas.add(pelicula);
			}
			
		//si introduce el año pero no el título
		} else if (!StringUtils.hasText(peliculaTitle)
				&& (peliculaYear != null)) {

			listaPeliculas = peliculaServiceI.obtenerPeliculaPorTituloOAnyo(peliculaTitle, peliculaYear);
		
		//si introduce el título pero no el año
		} else if (StringUtils.hasText(peliculaTitle)
				&& (peliculaYear==null)) {

			// Búsqueda por título o año
			final Pelicula peli = peliculaServiceI.obtenerPeliculaPorTitulo(peliculaTitle);	
			
			if (peli != null) {
				listaPeliculas.add(peli);
			}
			
		} else {
			throw new Exception("Parámetros de búsqueda erróneos.");
		}

		// Carga de datos al modelo
		model.addAttribute("peliculasListView", listaPeliculas);
		model.addAttribute("btnDropActorEnabled", Boolean.TRUE);

		return "showPeliculas";

	}

	/**
	 * Método que añade una nueva película a la base de datos mediante el objeto PeliculaModelo que pasamos por parámetro.
	 * @param newPelicula Objeto PeliculaModelo que usaremos para añadir una nueva película.
	 * @param result Respuesta que indica si hay algún error al introducir los parámetros.
	 * @return "redirect:showPeliculasView" Redirecciona a showPeliculasView  
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actAddPelicula")
	private String aniadirPelicula(@Valid @ModelAttribute PeliculaModelo newPelicula,  BindingResult result) throws Exception {
		

		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		} else {
			
			Pelicula p = new Pelicula();
			
			p.setTitle(newPelicula.getTitle());
			p.setYear(newPelicula.getYear());
			p.setDuration(newPelicula.getDuration());
			p.setSummary(newPelicula.getSummary());
			
			// Se añade la nueva película
			p.setActorPeliculas(null);
			peliculaServiceI.anadirPelicula(p);
		}

		return "redirect:showPeliculasView";
	}

	/**
	 * Método en el que a partir del id de actor pasado por parámetro obtenemos una película
	 * y añadimos el atributo al modelo. 
	 * @param peliculaId Id de la película.
	 * @param model Objeto Model
	 * @return "updatePelicula" Retorna la vista de actualización de una película. 
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actSetPelicula")
	public String actualizaSeteaPeliculas(@RequestParam String peliculaId, Model model) throws Exception {
		// Actualización de película
		
		Pelicula p = peliculaServiceI.obtenerPeliculaPorId(Long.valueOf(peliculaId));
	
		model.addAttribute("pelicula", p);
		
		return "updatePelicula";
	}
	
	/**
	 * Método que actualiza una película que le pasemos por parámetro.
	 * @param newPelicula Objeto Pelicula
	 * @param result Respuesta que indica si hay algún error al introducir los parámetros.
	 * @return "redirect:showPeliculasView" Redirecciona a showPeliculasView
	 * @throws Exception En el caso de que los parámetros de búsqueda sean erróneos. 
	 */
	@PostMapping("/actUpdatePelicula")
	public String actualizaPeliculas(@Valid @ModelAttribute Pelicula newPelicula, BindingResult result) throws Exception {
		// Actualización de película
		
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		} else {
			// Se añade la nueva película
			peliculaServiceI.actualizarPelicula(newPelicula);
		}

		return "redirect:showPeliculasView";
	}
}
