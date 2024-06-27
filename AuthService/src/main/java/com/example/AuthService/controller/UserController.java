package com.example.AuthService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthService.model.User;
import com.example.AuthService.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> signupSubmit(@RequestBody User user) {
		User foundUser = userService.findByUserName(user.getUsername());
		if (foundUser != null) {
			return ResponseEntity.ok("User already exist");
		} else {
			userService.saveUser(user);
			return ResponseEntity.ok("User registered successfully");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginSubmit(@RequestBody User user) {
		User foundUser = userService.findByUserName(user.getUsername());
		if (foundUser != null && user.getPassword().equals(foundUser.getPassword())) {
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.ok("Invalid username or password");
		}
	}
}
