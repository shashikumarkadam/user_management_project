package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;
@Service
public class UserService {

	
	UserRepository repository;
	
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}


	public List<User> getAllUser() {
		return repository.findAll();
	}


	public User getByUserId(Integer id) {
		return repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
	}

}
