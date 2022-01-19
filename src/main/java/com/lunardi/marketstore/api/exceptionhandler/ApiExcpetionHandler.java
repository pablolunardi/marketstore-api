package com.lunardi.marketstore.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lunardi.marketstore.api.exceptionhandler.Problem.Field;

@RestControllerAdvice
public class ApiExcpetionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Field> fields = ex.getFieldErrors().stream()
			.map(fieldError -> 
				 Field.builder()
						.name(fieldError.getField())
						.description(fieldError.getDefaultMessage())
						.build()
			)
			.collect(Collectors.toList());
		
		ProblemType problemType = ProblemType.INVALID_REQUEST;
		
		Problem problem = Problem.builder()
				.detail("One or more fields are invalid. Fill them out correctly and try again.")
				.fields(fields)
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(OffsetDateTime.now())
				.title(problemType.getTitle())
				.type(problemType.getUri())
				.userMessage("One or more fields are invalid. Fill them out correctly and try again.")
				.build();
		
		return ResponseEntity.badRequest().body(problem);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		
		Problem problem = Problem.builder()
				.detail(ex.getMessage())
				.status(HttpStatus.NOT_FOUND.value())
				.timestamp(OffsetDateTime.now())
				.title(problemType.getTitle())
				.type(problemType.getUri())
				.userMessage(ex.getMessage())
				.build();
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(problem);
	}
	

}
