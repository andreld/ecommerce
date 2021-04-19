package com.andre.ecommerce.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.andre.ecommerce.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> negocioHandler(NegocioException ex, WebRequest request) {
		Erro erro = new Erro();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		erro.setStatus(status.value());
		erro.setTituloString(ex.getMessage());
		erro.setDataHora(OffsetDateTime.now());
		
		return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro.Campo> campos = new ArrayList<Erro.Campo>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			campos.add(new Erro.Campo(((FieldError) error).getField(), error.getDefaultMessage()));
		}
		
		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setTituloString("Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente.");
		erro.setDataHora(OffsetDateTime.now());
		erro.setCampos(campos);
		
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}
}
