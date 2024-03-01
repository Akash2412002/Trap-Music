package com.trap_music.services;

import com.trap_music.entities.Users;

public interface UsersService {

	void registerUser(Users user);

	Users findByEmail(String email);

	boolean authenticate(String email, String password);

	void setPremiumAccount(Integer userId, boolean isPremium);

	boolean isPremiumAccount(Integer userId);

	Users getUser(String email);

	void updateUser(Users user);
	
	
}
