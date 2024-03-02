package com.trap_music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trap_music.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

}
