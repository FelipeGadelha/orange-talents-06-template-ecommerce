package br.com.zupacademy.felipe.gadelha.mercadolivre.api.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ERROR_FIELD = "Check the error field(s)";
	private static final String DOCUMENTATION = ", Check the Documentation";
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (Objects.isNull(body)) {
			body = ExceptionDetails
					.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.title(status.getReasonPhrase() + DOCUMENTATION)
					.details(ex.getMessage())
					.developerMessage(ex.getClass().getName())
					.build();			
		} else if (body instanceof ExceptionStatus) {
			ExceptionStatus exStatus = (ExceptionStatus) body;
			body = ExceptionDetails
					.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.type(exStatus.getUri())
					.title(exStatus.getTitle())
					.details(ex.getMessage())
					.developerMessage(ex.getClass().getName())
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
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
					.title(status.getReasonPhrase() + DOCUMENTATION)
					.details(ERROR_FIELD)
					.developerMessage(ex.getClass().getName())
					.errors(errorsMap)
					.build(), headers, status);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
				.timestamp(OffsetDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + DOCUMENTATION)
				.details(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<Object> handleAll(ResponseStatusException ex) {
		return new ResponseEntity<>(
				ExceptionDetails.builder()
				.timestamp(OffsetDateTime.now())
				.status(ex.getStatus().value())
				.title(ex.getStatus().getReasonPhrase() + DOCUMENTATION)
				.details(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build(), ex.getStatus());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(
			NullPointerException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<>(
				createExceptionDetails(
						ex, 
						HttpStatus.BAD_REQUEST, 
						ExceptionStatus.ILLEGAL_ARGUMENT, 
						ex.getMessage()),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
		String type = ex.getRequiredType().getSimpleName();
		var details = String.format("the URL parameter '%s' received the value '%s', which is of an invalid type, "
				+ "correct and enter a value compatible with the '%s' type.", ex.getName(), ex.getValue(), type);
		return new ResponseEntity<>(
				createExceptionDetails(
					ex, 
					HttpStatus.BAD_REQUEST, 
					ExceptionStatus.INVALID_PARAMETER, 
					details), 
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(StaleObjectStateException.class)
	public ResponseEntity<Object> handleStaleObjectStateException(
			StaleObjectStateException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
		return handleExceptionInternal(ex, ExceptionStatus.ILLEGAL_STATE, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	private ExceptionDetails createExceptionDetails(Exception ex, HttpStatus status,
			ExceptionStatus exceptionStatus, String detail) {
		
		return ExceptionDetails.builder()
			.timestamp(OffsetDateTime.now())
			.status(status.value())
			.type(exceptionStatus.getUri())
			.title(exceptionStatus.getTitle() + DOCUMENTATION)
			.details(detail)
			.developerMessage(ex.getClass().getName())
			.build();
	}

	private String exceptionReplace(Exception ex) {
		List<String> list = new ArrayList<>();
		Arrays.asList(ex.getClass().getSimpleName().split("")).forEach(f -> {
			if (f.matches("[A-Z]")) list.add(" " + f);
			else list.add(f);
		});
		return list.stream().collect(Collectors.joining()).trim() + DOCUMENTATION;
	}
}
