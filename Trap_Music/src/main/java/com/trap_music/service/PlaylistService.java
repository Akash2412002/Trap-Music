package com.trap_music.service;

import java.util.List;

import com.trap_music.entity.Playlist;

public interface PlaylistService {

	List<Playlist> getAllPlaylist();

	void createPlaylist(Playlist playlist);

	Playlist getPlaylistById(int playlistId);

	void deletePlaylist(int playlistId);

	
	
	
}
