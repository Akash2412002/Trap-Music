package com.trap_music.services;

import com.trap_music.entities.Playlist;

public interface PlaylistService {

	void addSongToPlaylist(Integer playlistId, Integer songId);

	void makeSongFavorite(int id, Integer songId);

	void createPlaylist(Playlist playlist);

}
