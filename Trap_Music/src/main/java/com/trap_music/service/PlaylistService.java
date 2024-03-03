package com.trap_music.service;

import java.util.List;

import com.trap_music.entity.Playlist;
import com.trap_music.entity.Song;

public interface PlaylistService {

	void addPlaylist(Playlist playlist);

	List<Playlist> fetchPlaylists();

	
	
	
	
}
