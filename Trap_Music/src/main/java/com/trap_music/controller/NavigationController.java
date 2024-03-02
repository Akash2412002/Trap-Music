package com.trap_music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
	 	
		@GetMapping("/")
		public String index() {
			return "index";
		}

	    @GetMapping("/auth/register")
	    public String register() {
	        return "register";
	    }

	    @GetMapping("/auth/login")
	    public String login() {
	        return "login";
	    }

	    @GetMapping("/auth/adminhomepage")
	    public String adminHomepage() {
	        return "adminhomepage";
	    }

	    @GetMapping("/auth/customerhomepage")
	    public String customerHomepage() {
	        return "customerhomepage";
	    }

	    @GetMapping("/songs/addsongs")
	    public String addSongs() {
	        return "addsongs";
	    }

	    @GetMapping("/songs/createplaylist")
	    public String createPlaylist() {
	        return "createplaylist";
	    }


	    @GetMapping("/auth/subscription")
	    public String payment() {
	        return "subscription";
	    }
}
