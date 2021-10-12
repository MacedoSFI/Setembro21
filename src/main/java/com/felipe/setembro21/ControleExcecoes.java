package com.felipe.setembro21;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String msgErro = "";
		
		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> listaErros = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError erro : listaErros) {
				msgErro += erro.getDefaultMessage() + "\n";
			}
		} else msgErro = ex.getMessage();
		
		ObjetoErro objErro = new ObjetoErro();
		objErro.setError(msgErro);
		objErro.setCode(status.value() + "==> " + status.getReasonPhrase());
		
		
		return new ResponseEntity<>(objErro, headers, status);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegrity(Exception ex) {
		String msgErro = "";
		if (ex instanceof DataIntegrityViolationException) {
			msgErro = ((DataIntegrityViolationException) ex).getCause().getMessage();
		} else if (ex instanceof ConstraintViolationException) {
			msgErro = ((ConstraintViolationException) ex).getCause().getMessage();
		}  else if (ex instanceof SQLException) {
			msgErro = ((SQLException) ex).getCause().getMessage();
		} else msgErro = ex.getMessage();
		
		ObjetoErro objErro = new ObjetoErro();
		objErro.setError(msgErro);
		objErro.setCode(HttpStatus.INTERNAL_SERVER_ERROR + "==> " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	
		return new ResponseEntity<>(objErro, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
