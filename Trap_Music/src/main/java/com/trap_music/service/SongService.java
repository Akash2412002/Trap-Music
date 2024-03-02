package com.trap_music.service;

import java.util.List;

import com.trap_music.entity.Song;

public interface SongService {
	String addSongs(Song song);

    boolean songExists(String name);

    List<Song> fetchAllSongs();

    void updateSong(Song song);

    List<Song> searchSongs(String keyword);

   
}
