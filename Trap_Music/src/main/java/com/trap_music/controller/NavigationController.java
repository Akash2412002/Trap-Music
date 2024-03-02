package com.trap_music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	 	
		@GetMapping("/")
		public String index() {
			return "index";
		}

	    @GetMapping("/auth/register")
	    public String register() {
	        return "/auth/register";
	    }

	    @GetMapping("/auth/login")
	    public String login() {
	        return "/auth/login";
	    }

	    @GetMapping("/auth/adminhomepage")
	    public String adminHomepage() {
	        return "/auth/adminhomepage";
	    }

	    @GetMapping("/auth/customerhomepage")
	    public String customerHomepage() {
	        return "/auth/customerhomepage";
	    }

	    @GetMapping("/songs/addsongs")
	    public String addSongs() {
	        return "/songs/addsongs";
	    }

	    @GetMapping("/songs/createplaylist")
	    public String createPlaylist() {
	        return "/songs/createplaylist";
	    }


	    @GetMapping("/auth/subscription")
	    public String payment() {
	        return "/auth/subscription";
	    }
	    
	    @GetMapping("/logout")
	    public String logoutUser(HttpSession session) {
	        session.invalidate(); // Invalidate session on logout
	        return "index";
	    }
}
