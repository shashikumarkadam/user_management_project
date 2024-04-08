package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;
@Service
public class AdminService {

	UserRepository repository;
	
	
	public AdminService(UserRepository repository) {
		this.repository = repository;
	}


	public User creatUsers(User user) {
		return repository.saveAndFlush(user);
	}


	public List<User> getUsers() {
		return repository.findAll();
	}


	public User updateUsers(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}


	public void deleteByUserId(Integer id) {

		repository.deleteById(id);
	}

}
