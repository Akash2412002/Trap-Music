package com.trap_music.service;

import java.util.List;

import com.trap_music.entity.Playlist;
import com.trap_music.entity.User;

public interface PlaylistService {

	void deletePlaylist(int playlistId);

	void addPlaylist(Playlist playlist, User user);

	List<Playlist> fetchPlaylistsByUser(User user);
	
}
