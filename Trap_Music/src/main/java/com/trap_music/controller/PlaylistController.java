package com.trap_music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trap_music.entity.Playlist;
import com.trap_music.entity.Song;
import com.trap_music.service.PlaylistService;
import com.trap_music.service.SongService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    public PlaylistService playlistService;
    
    @Autowired
    public SongService songService;

    // Get all playlists
    @GetMapping("/viewplaylist")
    public List<Playlist> getAllPlaylist() {
        return playlistService.getAllPlaylist();
    }

    // Create a new playlist
    @PostMapping("/createplaylist")
    public void createPlaylist(@RequestBody Playlist playlist) {
        playlistService.createPlaylist(playlist);
    }

    // Add songs to an existing playlist
    @PostMapping("/{playlistId}/add-songs")
    public void addSongsToPlaylist(@PathVariable int playlistId, @RequestBody List<Song> songs) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        if (playlist != null) {
            for (Song song : songs) {
                song.getPlaylist().add(playlist);
                songService.updateSong(song);
            }
        }
    }

    // Delete a playlist by ID
    @DeleteMapping("/{playlistId}")
    public void deletePlaylist(@PathVariable int playlistId) {
        playlistService.deletePlaylist(playlistId);
    }
}

