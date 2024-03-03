package com.trap_music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entity.Playlist;
import com.trap_music.entity.Song;
import com.trap_music.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	 @Autowired
	    public PlaylistRepository playlistRepository;

	@Override
	public void addPlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchPlaylists() {
		return playlistRepository.findAll();
	}

	    
}
