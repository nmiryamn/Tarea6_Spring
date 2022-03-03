package com.dam.tarea6.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.tarea6.entidades.Actor;
import com.dam.tarea6.entidades.Pelicula;
import com.dam.tarea6.repositorio.PeliculaRepositorio;

@Service
public class PeliculaServiceImpl implements PeliculaServiceI{

	@Autowired
	private PeliculaRepositorio peliculaRepositorio;
	
	@Override
	public List<Pelicula> obtenerTodasPeliculas() {
		return peliculaRepositorio.findAll();
	}

	@Override
	public void anadirPelicula(Pelicula pelicula) {
		peliculaRepositorio.save(pelicula);	
		
	}

	@Override
	public void eliminarPelicula(long IdPelicula) {
		peliculaRepositorio.deleteById(IdPelicula);
		
	}

	@Override
	public void actualizarPelicula(Pelicula pelicula) {
		peliculaRepositorio.save(pelicula);
	}

	@Override
	public Pelicula obtenerPeliculaPorTitulo(String titulo) {
		final Pelicula peli = peliculaRepositorio.findByTitle(titulo);
		return peli;
		 
	}

	@Override
	public Pelicula obtenerPeliculaPorTituloYAnyo(String title, int anyo) {
		final Pelicula peli = peliculaRepositorio.findByTitleAndYear(title, anyo);
		return peli;
	}

	@Override
	public List<Pelicula> obtenerPeliculaPorTituloOAnyo(String title, int anyo) {
		return peliculaRepositorio.findByTitleOrYear(title, anyo);
	}

	@Override
	public Pelicula obtenerPeliculaPorId(Long id) {
		return peliculaRepositorio.getById(id);
	}

}
