package com.trap_music.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trap_music.entity.Song;
import com.trap_music.entity.User;
import com.trap_music.service.SongService;
import com.trap_music.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RequestMapping("/songs")
@Controller
public class SongController {
    
	
    @Autowired
    public SongService songService;

    @Autowired
    public UserService userService;
    
    
    @PostMapping("/addsongs")
    public String addSongs(@ModelAttribute Song song, Model model) {
        boolean status = songService.songExists(song.getName());
        if (!status) {
            songService.addSong(song);
            return "redirect:/songs/viewsongs";
        } else {
            return "redirect:/auth/adminhomepage";
        }
    }
    
    @GetMapping("/viewsongs")
    public String viewSongs(Model model) {
        List<Song> songslist = songService.fetchAllSongs();
        model.addAttribute("songslist", songslist);
        return "songs/viewsongs";
    }
    
    @GetMapping("/search")
    public String searchSongs(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            List<Song> searchResults = songService.searchSongs(keyword);
            model.addAttribute("searchResults", searchResults);
        }
        return "songs/searchresults";
    }
    
    @GetMapping("/togglefavorite") 
    public String toggleFavorite(@RequestParam("songId") int songId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Song song = songService.getSongById(songId);

        if (user != null && song != null) {
            List<Song> favoriteSongs = user.getFavoriteSongs();

            if (favoriteSongs.contains(song)) {
                favoriteSongs.remove(song);
                song.getFavoritedBy().remove(user);
            } else {
                favoriteSongs.add(song);
                song.getFavoritedBy().add(user);
            }
            userService.updateUser(user);
            songService.updateSong(song);
            return "redirect:/songs/favorites";
        } else {
            return "redirect:/songs/viewsongs";
        }
    }


    
    @GetMapping("/favorites")
    public String viewFavorites(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Song> favorites = user.getFavoriteSongs();
            model.addAttribute("favorites", favorites);
            return "songs/favorites"; // Return view for displaying favorite songs
        } else {
            // Handle case when user is not authenticated
            return "redirect:/auth/login"; // Redirect to login page or display an error message
        }
    }


}
