package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;
@Service
public class ManagerService {

	UserRepository repository;
	
	
	
	public ManagerService(UserRepository repository) {
		this.repository = repository;
	}



	public List<User> getAllUser() {
		return repository.findAll();
	}



	public User getByUserId(Integer id) {
		// when user not found throw runtime exception 'User not found'.
		return repository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
	}

}
