package com.cdac.garage.exceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdac.garage.DTO.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ApiResponse handleRuntimeException(RuntimeException e) {
		System.out.println("runtime exception: " + e);
		return new ApiResponse(e.getMessage());
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		List<ObjectError> globalErrors = e.getGlobalErrors();
		List<FieldError> fieldErrors = e.getFieldErrors();
		
		Map<String,String> map1 = globalErrors.stream().collect(Collectors.toMap(ObjectError::getObjectName, ObjectError::getDefaultMessage));
		Map<String,String> map2 = fieldErrors.stream().collect(Collectors.toMap(FieldError::getObjectName, FieldError::getDefaultMessage));
		
		map1.putAll(map2);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map1);
	}
	
	
	
}
