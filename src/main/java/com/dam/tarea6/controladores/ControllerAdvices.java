package com.dam.tarea6.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Usuario
 *
 */
@ControllerAdvice
public class ControllerAdvices {
	
	 //Capta cualquier excepción en los métodos de cualquier controlador.

	/**
	 * Método que controla las excepciones
	 * @param req Objeto HttpServletRequest
	 * @param e Objeto Exception
	 * @param model Objeto Modelo
	 * @return "error". Retorna la vista de error
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {

		// Respuesta.
		model.addAttribute("errorMsg", e.getMessage());
		System.out.println(e.getCause());

		return "error";
	}

}
