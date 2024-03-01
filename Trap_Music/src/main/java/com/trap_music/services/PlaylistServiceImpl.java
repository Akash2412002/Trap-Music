package com.trap_music.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trap_music.entities.Playlist;
import com.trap_music.entities.Songs;
import com.trap_music.entities.Users;
import com.trap_music.repositories.PlaylistRepository;
import com.trap_music.repositories.SongsRepository;
import com.trap_music.repositories.UsersRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	 @Autowired
	    public PlaylistRepository playlistRepository;
	 @Autowired
	 	public SongsRepository songsRepository;
	 @Autowired
	 	public UsersRepository userRepository;

	 @Override
	 public void addSongToPlaylist(Integer playlistId, Integer songId) {
	     Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
	     if (playlist != null) {
	         Songs song = songsRepository.findById(songId).orElse(null);
	         if (song != null) {
	             playlist.getSongs().add(song);
	             playlistRepository.save(playlist);
	         }
	     }
	 }


	 @Override
	 public void makeSongFavorite(int userId, Integer songId) {
	     // Retrieve the user's playlist or favorite songs list
	     Users user = userRepository.findById(userId).orElse(null);
	     if (user == null) {
	         // Handle the case where the user doesn't exist
	         return;
	     }
	     List<Songs> favoriteSongs = user.getFavoriteSongs();
	     
	     // Check if the song already exists in the user's favorite songs list
	     boolean isFavorite = false;
	     for (Songs song : favoriteSongs) {
	         if (song.getId().equals(songId)) {
	             // Remove the song from the favorite songs list to "unfavorite" it
	             favoriteSongs.remove(song);
	             isFavorite = true;
	             break;
	         }
	     }
	     
	     // If the song doesn't exist, add it to the list to mark it as favorite
	     if (!isFavorite) {
	         Songs songToAdd = songsRepository.findById(songId).orElse(null);
	         if (songToAdd != null) {
	             favoriteSongs.add(songToAdd);
	         } else {
	             // Handle the case where the song doesn't exist
	         }
	     }
	     
	     // Save the updated playlist or favorite songs list back to the database
	     user.setFavoriteSongs(favoriteSongs);
	     userRepository.save(user);
	 }


	@Override
	public void createPlaylist(Playlist playlist) {
		 playlistRepository.save(playlist);
		
	}

}
