package com.trap_music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entity.Playlist;
import com.trap_music.entity.User;
import com.trap_music.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	 @Autowired
	    public PlaylistRepository playlistRepository;

	@Override
	public void addPlaylist(Playlist playlist,User user ) {
		playlist.setUser(user); 
		playlistRepository.save(playlist);
		
	}

	@Override
    public void deletePlaylist(int playlistId) {
        playlistRepository.deleteById(playlistId);
    }


	@Override
	public List<Playlist> fetchPlaylistsByUser(User user) {
		return playlistRepository.findByUser(user);
	}   
}
