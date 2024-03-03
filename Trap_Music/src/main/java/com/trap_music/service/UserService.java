package com.trap_music.service;

import java.util.List;

import com.trap_music.entity.Song;
import com.trap_music.entity.User;

public interface UserService {
public String addUser(User user);
	
	public boolean emailExists(String email);
	
	public boolean validateUser(String email, String password);
	
	public String getRole(String email);

	public User getUser(String email);

	public void updateUser(User user);

	public List<Song> getFavoriteSongs(User user);

}
