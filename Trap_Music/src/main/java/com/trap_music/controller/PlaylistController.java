package com.trap_music.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trap_music.entities.Playlist;
import com.trap_music.services.PlaylistService;

@RestController
public class PlaylistController {
    @Autowired
    public PlaylistService playlistService;

    @PostMapping("/create-playlist")
    public ResponseEntity<String> createPlaylist(@RequestBody Playlist playlist, Principal principal) {
        String email = principal.getName();
        playlistService.createPlaylist(playlist);
        return ResponseEntity.ok("Playlist created successfully");
    }
}
