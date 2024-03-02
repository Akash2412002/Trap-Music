package com.trap_music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entity.Song;
import com.trap_music.repository.SongRepository;

@Service
public class SongServiceImpl implements SongService{
	 @Autowired
	    public SongRepository songRepository;

	    @Override
	    public String addSongs(Song song) {
	        songRepository.save(song);
	        return "Song added successfully";
	    }

	    @Override
	    public boolean songExists(String name) {
	        return songRepository.existsByName(name);
	    }

	    @Override
	    public List<Song> fetchAllSongs() {
	        return songRepository.findAll();
	    }

	    @Override
	    public void updateSong(Song song) {
	        songRepository.save(song);
	    }

	    @Override
	    public List<Song> searchSongs(String keyword) {
	        return songRepository.findByNameContainingIgnoreCaseOrArtistContainingIgnoreCase(keyword, keyword);
	    }

}
