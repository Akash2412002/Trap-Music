package com.trap_music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trap_music.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

}
