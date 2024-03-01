package com.trap_music.services;

import java.util.List;

import com.trap_music.entities.Songs;

public interface SongsService {

	Songs addSong(Songs song);
    
    List<Songs> getAllSongs();

	Songs getSongByName(String name);

	List<Songs> searchSongs(String keyword);
	
}
