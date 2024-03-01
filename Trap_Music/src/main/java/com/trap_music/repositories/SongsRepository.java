package com.trap_music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trap_music.entities.Songs;

public interface SongsRepository extends JpaRepository<Songs, Integer> {
	 Songs findByName(String name);

	 List<Songs> findByNameContainingIgnoreCase(String keyword);

	 List<Songs> searchSongs(String keyword);
}
