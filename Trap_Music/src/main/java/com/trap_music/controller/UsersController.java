package com.trap_music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trap_music.entities.Users;
import com.trap_music.services.SongsService;
import com.trap_music.services.UsersService;

@RestController
public class UsersController {

	    @Autowired
	    public UsersService usersService;
	    
	    @Autowired
	    public SongsService songsService;

	    @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@RequestBody Users user) {
	        // Check if email is already registered
	        if (usersService.findByEmail(user.getEmail()) != null) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
	        }

	        // Register the user
	        usersService.registerUser(user);

	        // Return success response
	        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	    }
	    
}
