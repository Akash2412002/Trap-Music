package com.trap_music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entity.Song;
import com.trap_music.repository.SongRepository;
import com.trap_music.repository.UserRepository;

@Service
public class SongServiceImpl implements SongService{
	@Autowired
	public SongRepository songRepository;
	
	@Autowired
	public UserRepository userRepository;

	@Override
	public boolean songExists(String name) {
		Song song = songRepository.findByName(name);
		return song != null;
	}

	@Override
	public void addSong(Song song) {
		 songRepository.save(song);
	}

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songslist = songRepository.findAll();
		return songslist;
	}

	@Override
	public List<Song> searchSongs(String keyword) {
		return songRepository.findByNameContainingIgnoreCaseOrArtistContainingIgnoreCase(keyword, keyword);
	}	
	
	@Override
    public void deleteSong(int songId) {
        songRepository.deleteById(songId);
    }

	@Override
	public void updateSong(Song song) {
		songRepository.save(song);	
	}
	

	@Override
    public Song getSong(int songId) {
        Optional<Song> songOptional = songRepository.findById(songId);
        return songOptional.orElse(null);
    }
 
}
