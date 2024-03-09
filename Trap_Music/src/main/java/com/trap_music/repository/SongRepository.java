package com.trap_music.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.trap_music.entity.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {

	Song findByName(String name);

	List<Song> findByNameContainingIgnoreCaseOrArtistContainingIgnoreCase(String keyword, String keyword2);

	List<Song> findByFavoriteTrue();

}
