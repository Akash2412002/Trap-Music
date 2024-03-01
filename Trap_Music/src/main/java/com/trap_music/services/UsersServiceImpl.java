package com.trap_music.services;

import com.trap_music.entities.Users;
import com.trap_music.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public void registerUser(Users user) {
        user.setRole("USER"); // Set default role as USER
        userRepository.save(user);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean authenticate(String email, String password) {
        Users user = findByEmail(email);
        return user != null && password.equals(user.getPassword());
    }
    
    @Override
    public void setPremiumAccount(Integer userId, boolean isPremium) {
        Users user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setPremiumAccount(isPremium);
            userRepository.save(user);
        }
    }

    @Override
    public boolean isPremiumAccount(Integer userId) {
        Users user = userRepository.findById(userId).orElse(null);
        return user != null && user.isPremiumAccount();
    }

	@Override
	public Users getUser(String email) {
		 return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		 userRepository.save(user);
		
	}
}
