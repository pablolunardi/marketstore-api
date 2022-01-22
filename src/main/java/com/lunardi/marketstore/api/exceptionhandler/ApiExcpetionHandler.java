package com.lunardi.marketstore.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lunardi.marketstore.api.exceptionhandler.Problem.Field;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.EntityInUseException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiExcpetionHandler extends ResponseEntityExceptionHandler {
	
	public static final String MSG_GENERIC_SYSTEM_ERROR = "An exepected error has happened, if the problem continues, please contact the system admin";
	
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
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
			ProblemType problemType = ProblemType.INVALID_REQUEST;
			
			Problem problem = Problem.builder()
					.detail("One or more fields are invalid. Fill them out correctly and try again.")
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
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<Object> handleEntityInUseException(EntityInUseException ex) {
		
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		
		Problem problem = Problem.builder()
				.detail(ex.getMessage())
				.status(HttpStatus.CONFLICT.value())
				.timestamp(OffsetDateTime.now())
				.title(problemType.getTitle())
				.type(problemType.getUri())
				.userMessage(ex.getMessage())
				.build();
		
		
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(problem);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		
		ProblemType problemType = ProblemType.BUSSINESS_ERROR;
		
		Problem problem = Problem.builder()
				.detail(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(OffsetDateTime.now())
				.title(problemType.getTitle())
				.type(problemType.getUri())
				.userMessage(ex.getMessage())
				.build();
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex) {
		log.error("Unhandled exception", ex);
		
		ProblemType problemType = ProblemType.SYSTEM_ERROR;
		
		Problem problem = Problem.builder()
				.detail(ex.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(OffsetDateTime.now())
				.title(problemType.getTitle())
				.type(problemType.getUri())
				.userMessage(MSG_GENERIC_SYSTEM_ERROR)
				.build();
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(problem);
		
	}

}
