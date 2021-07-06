package br.com.zupacademy.felipe.gadelha.mercadolivre.api.handler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> errors = ex.getBindingResult().getGlobalErrors();
		
		Map<String, Set<String>> errorsMap = fieldErrors
				.stream()
				.collect(Collectors
						.groupingBy(FieldError::getField, 
								Collectors.mapping(FieldError::getDefaultMessage, 
										Collectors.toSet())));
		if(errorsMap.isEmpty()) {
			errorsMap = errors
					.stream()
					.collect(Collectors
							.groupingBy(ObjectError::getCode, 
									Collectors.mapping(ObjectError::getDefaultMessage,
											Collectors.toSet())));
		}
		return new ResponseEntity<>(
				ValidationExceptionDetails
					.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.title(status.getReasonPhrase() + " Exception, Check the Documentation")
					.details("Check the error field(s)")
					.developerMessage(ex.getClass().getName())
					.errors(errorsMap)
					.build(), headers, status);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
				.timestamp(OffsetDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " Exception, Check the Documentation")
				.details(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
				.timestamp(OffsetDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " Exception, Check the Documentation")
				.details(ex.getCause().getMessage())
				.developerMessage(ex.getClass().getName())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
