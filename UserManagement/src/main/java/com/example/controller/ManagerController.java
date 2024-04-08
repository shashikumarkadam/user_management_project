package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.ManagerService;

@RestController
@RequestMapping("/manager")
@PreAuthorize("hasRole('MANAGER')")		//first check this perticular role is "MANAGER".
public class ManagerController {

	ManagerService service;
	
	
	public ManagerController(ManagerService service) {
		this.service = service;
	}


	@GetMapping("/get/all")
	public ResponseEntity<List<User>> getAll(){
		List<User> response=service.getAllUser();
		
		if(!ObjectUtils.isEmpty(response)) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get/user/{id}")
	public ResponseEntity<User> getById(@PathVariable Integer id){
		User response=service.getByUserId(id);
		
		if(!ObjectUtils.isEmpty(response)) {
			return new ResponseEntity<User>(response,HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(response,HttpStatus.NOT_FOUND);
		}
	}
	
}
