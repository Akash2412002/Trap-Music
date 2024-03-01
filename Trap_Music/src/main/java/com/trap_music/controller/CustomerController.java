package com.trap_music.controller;

import java.security.Principal;
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

import com.trap_music.entities.Playlist;
import com.trap_music.entities.Songs;
import com.trap_music.entities.Users;
import com.trap_music.services.PlaylistService;
import com.trap_music.services.SongsService;
import com.trap_music.services.UsersService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    public PlaylistService playlistService;

    @Autowired
    public UsersService usersService;
    
    @Autowired
    public SongsService songService;

    @PostMapping("/create-playlist")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> createPlaylist(@RequestBody Playlist playlist, Principal principal) {
        String email = principal.getName();
        Users user = usersService.findByEmail(email);
        playlist.setUser(user);
        playlistService.createPlaylist(playlist);
        return ResponseEntity.ok("Playlist created successfully");
    }

    @PostMapping("/add-song-to-playlist")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addSongToPlaylist(@RequestParam Integer playlistId, @RequestParam Integer songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok("Song added to playlist successfully");
    }

    @PostMapping("/make-song-favorite")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> makeSongFavorite(@RequestParam Integer songId, Principal principal) {
        String email = principal.getName();
        Users user = usersService.findByEmail(email);
        playlistService.makeSongFavorite(user.getId(), songId);
        return ResponseEntity.ok("Song added to favorites successfully");
    }

    @GetMapping("/search-songs")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Songs>> searchSongs(@RequestParam String keyword) {
        List<Songs> songs = songService.searchSongs(keyword);
        return ResponseEntity.ok(songs);
    }
}
