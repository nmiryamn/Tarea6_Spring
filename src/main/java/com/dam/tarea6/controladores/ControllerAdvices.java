package com.dam.tarea6.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.dam.tarea6.exception.InvalidDataException;

/**
 * 
 * @author Usuario
 *
 */
@ControllerAdvice
public class ControllerAdvices {

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
	
	@ExceptionHandler(InvalidDataException.class)
	public String invalidDataException(InvalidDataException ex, WebRequest request, Model model) {

		List<String> listErrors = new ArrayList<String>();
		
      List<FieldError> errors = ex.getResult().getFieldErrors();
   
      for (FieldError error : errors) {
          System.out.println(error.getDefaultMessage());
          listErrors.add(error.getDefaultMessage());
          
      }
      
      model.addAttribute("errorMsg", listErrors);
      
      return "error";
  
	
	}

}
