package com.trap_music.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entities.Songs;
import com.trap_music.repositories.SongsRepository;

@Service
public class SongsServiceImpl implements SongsService {
	@Autowired
    public SongsRepository songsRepository;

	@Override
	public Songs addSong(Songs song) {
		return songsRepository.save(song);
	}

	@Override
	public List<Songs> getAllSongs() {
		return songsRepository.findAll();
	}

	@Override
	public Songs getSongByName(String name) {
		return songsRepository.findByName(name);
	}

	@Override
	public List<Songs> searchSongs(String keyword) {
		return songsRepository.searchSongs(keyword);
	}

	
}
