package com.trap_music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entity.User;
import com.trap_music.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	 @Autowired
	    public UserRepository userRepository;

	    @Override
	    public String addUser(User user) {
	        userRepository.save(user);
	        return "User added successfully";
	    }

	    @Override
	    public boolean emailExists(String email) {
	        return userRepository.existsByEmail(email);
	    }

	    @Override
	    public boolean validateUser(String email, String password) {
	        User user = userRepository.findByEmail(email);
	        return user != null && user.getPassword().equals(password);
	    }

	    @Override
	    public String getRole(String email) {
	        User user = userRepository.findByEmail(email);
	        return user != null ? user.getRole() : null;
	    }

	    @Override
	    public User getUser(String email) {
	        return userRepository.findByEmail(email);
	    }

	    @Override
	    public void updateUser(User user) {
	        userRepository.save(user);
	    }
}
