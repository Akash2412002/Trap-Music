package com.trap_music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trap_music.entity.Song;
import com.trap_music.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {
    
    @Autowired
    private SongService songsService;

    // Add songs

    @PostMapping("/addsongs")
    public String addSongs(@ModelAttribute Song song) {
        boolean status = songsService.songExists(song.getName());
        if (!status) {
            songsService.addSongs(song);
            // Redirect to the admin homepage after successfully adding the new song
            return "redirect:/auth/adminhomepage";
        } else {
            // If the song already exists, stay on the same page (addsongs)
            return "redirect:/songs/addsongs";
        }
    }


    // View songs
    @GetMapping("/map-viewsongs")
    public String viewSongs(Model model) {
        List<Song> songslist = songsService.fetchAllSongs();
        model.addAttribute("songslist", songslist);
        return "viewsongs";
    }

    // View customer songs
    @GetMapping("viewsongs")
    public String viewCustomerSongs(Model model) {
        // Assuming premium customer status
        boolean primeCustomerStatus = true;
        if (primeCustomerStatus) {
            List<Song> songslist = songsService.fetchAllSongs();
            model.addAttribute("songslist", songslist);
            return "viewsongs";
        } else {
            return "subscription";
        }
    }
}
