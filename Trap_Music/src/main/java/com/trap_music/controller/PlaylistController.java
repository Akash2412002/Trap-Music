package com.trap_music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trap_music.entity.Playlist;
import com.trap_music.entity.Song;
import com.trap_music.service.PlaylistService;
import com.trap_music.service.SongService;

@RequestMapping("/songs")
@Controller
public class PlaylistController 
{
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	SongService songService;
	
	@GetMapping("/createplaylist")
	public String createPlayList(Model model) {
		List<Song> songslist=songService.fetchAllSongs(); //Fetching the songs using song service
		model.addAttribute("songslist",songslist); //Adding the songs in the model
		return "songs/createplaylist"; //sending createplaylist
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
	    playlistService.addPlaylist(playlist);	// Add the playlist to the database using the playlist service
	    List<Song> songs = playlist.getSongs();
	    for (Song song : songs) {
	        song.getPlaylist().add(playlist); // Add the playlist to the song's playlist collection
	        songService.updateSong(song); // Update the song in the database
	    }
	    return "redirect:/songs/viewplaylist"; // Redirect to the viewPlaylists page
	}

	
	@GetMapping("/viewplaylist")
	public String viewPlaylists(Model model) {
	    List<Playlist> playlists = playlistService.fetchPlaylists(); // Fetch all playlists from the database
	    model.addAttribute("playlists", playlists); // Add playlists to the model
	    return "songs/viewplaylist"; // Return the viewplaylist.html page
	}

	
}

