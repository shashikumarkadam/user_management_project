package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.AdminService;

@RestController
@RequestMapping("/Admin")	// only access admin
@PreAuthorize("hasRole('ADMIN')")	//first check this perticular role is "ADMIN".
public class AdminController {

	AdminService service;
	
	
	// using constructor injection
	public AdminController(AdminService service) {
		this.service = service;
	}
	
	
	// create new data using "create/user" url
	@PostMapping("/create/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User response=service.creatUsers(user);
		if(!ObjectUtils.isEmpty(response)) {		 // if object is empty then response to send end user bad_request.
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
			
	}
	
	// fetch list of all data.
	@GetMapping("/get/user")
	public ResponseEntity<List<User>> getUser(){
		List<User> response=service.getUsers();
		if(!ObjectUtils.isEmpty(response)) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	// update exist user only admin control.
	@PutMapping("/update/user")
	public ResponseEntity<Integer> updateUser(@RequestBody User user){
		User response=service.updateUsers(user);
		if(!ObjectUtils.isEmpty(response)) {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// delete the existent user via userId. 
	@DeleteMapping("/delete/user/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.deleteByUserId(id);
	}
}
