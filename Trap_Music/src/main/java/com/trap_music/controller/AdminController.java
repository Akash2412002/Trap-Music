package com.trap_music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trap_music.entities.Songs;
import com.trap_music.services.SongsService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public SongsService songService;

    @PostMapping("/add-song")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addSong(@RequestBody Songs song) {
        songService.addSong(song);
        return ResponseEntity.ok("Song added successfully");
    }

    @GetMapping("/view-songs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Songs>> viewSongs() {
        List<Songs> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/search-songs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Songs>> searchSongs(@RequestParam String keyword) {
        List<Songs> songs = songService.searchSongs(keyword);
        return ResponseEntity.ok(songs);
    }
}
