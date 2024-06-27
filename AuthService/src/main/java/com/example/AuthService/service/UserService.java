package com.example.AuthService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.model.User;
import com.example.AuthService.repository.UserRepository;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public User findByUserName(String username) {
		User user = null;
		try {
			user = userRepository.findByUsername(username);
		} catch (Exception e) {
			logger.info("Exception in findByUserName " + e.getLocalizedMessage());
		}
		return user;
	}

	public void saveUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			logger.info("Exception in saveUser " + e.getLocalizedMessage());
		}
	}
}
