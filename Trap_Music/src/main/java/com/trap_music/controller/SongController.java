package com.trap_music.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.trap_music.entity.Song;
import com.trap_music.service.SongService;
import com.trap_music.service.UserService;

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
      

    @DeleteMapping("/{songId}")
    public ResponseEntity<String> deleteSong(@PathVariable("songId") int songId) {
        songService.deleteSong(songId);
        return ResponseEntity.ok("Song deleted successfully");
    }
    
}
