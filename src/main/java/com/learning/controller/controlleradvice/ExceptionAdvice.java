package com.learning.controller.controlleradvice;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.Exception.AlreadyExistsException;
import com.learning.Exception.IdNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {
 
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(AlreadyExistsException e){
		HashMap<String, String> map=new HashMap<>();
		map.put("messeage", "Record already exists"+e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e){
		HashMap<String, String> map=new HashMap<>();
		map.put("messeage", "Unknown exception "+e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> exceptionHandler(IdNotFoundException e){
		HashMap<String, String> map=new HashMap<>();
		map.put("messeage", "id not found exception "+e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
}
