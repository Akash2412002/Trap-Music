package com.trap_music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entity.Playlist;
import com.trap_music.repository.PlaylistRepository;

@Service
public class PlayListServiceImpl implements PlaylistService {
	 @Autowired
	    public PlaylistRepository playlistRepository;

	@Override
	public List<Playlist> getAllPlaylist() {
		return playlistRepository.findAll();
	}

	@Override
	public void createPlaylist(Playlist playlist) {
		  playlistRepository.save(playlist);
		
	}

	@Override
	public Playlist getPlaylistById(int playlistId) {
		return playlistRepository.findById(playlistId).orElse(null);
	}

	@Override
	public void deletePlaylist(int playlistId) {
		playlistRepository.deleteById(playlistId);
		
	}

	    
}
