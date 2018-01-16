package br.com.baiocchilousa.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.baiocchilousa.brewer.service.exception.NomeEstiloJaCadastradoException;

/**
 * Classe anotada com @ControllerAdvice para tratar excessões não tratadas de todos os Controllers
 * @author Leonardo Baiocchi Lousa
 *
 */

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(NomeEstiloJaCadastradoException.class)
	public ResponseEntity<String> handlerNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
