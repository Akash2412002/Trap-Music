package com.trap_music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trap_music.entities.Songs;
import com.trap_music.services.SongsService;

@RestController
	@RequestMapping("/songs")
	public class SongsController {

	    @Autowired
	    public SongsService songsService;

	    @GetMapping("/search/{name}")
	    public ResponseEntity<Songs> getSongByName(@PathVariable String name) {
	        Songs song = songsService.getSongByName(name);
	        if (song != null) {
	            return ResponseEntity.ok(song);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping
	    public ResponseEntity<String> addSong(@RequestBody Songs song) {
	        songsService.addSong(song);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Song added successfully");
	    }

}
