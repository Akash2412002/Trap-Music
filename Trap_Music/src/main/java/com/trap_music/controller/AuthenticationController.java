package com.trap_music.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trap_music.entity.User;
import com.trap_music.service.SongService;
import com.trap_music.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/auth")
@Controller
public class AuthenticationController {

    @Autowired
    public UserService userService;
    
    @Autowired
    public SongService songService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, HttpSession session) {
        boolean userExists = userService.emailExists(user.getEmail());
        if (!userExists) {
            userService.addUser(user);
            session.setAttribute("user", user); // Store the entire user object in the session
            return "redirect:login"; // Redirect to the login page after successful registration
        } else {
            // Redirect to login page with error message if user already exists
            return "redirect:login?error=user already exists";
        }
    }


    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
        if (userService.validateUser(email, password)) {
            User user = userService.getUser(email); // Retrieve the entire user object
            session.setAttribute("email", email); // Store the entire user object in the session
            if (userService.getRole(email).equals("admin")) {
                return "redirect:adminhomepage";
            } else if (user.isPremiumAccount()) {
                return "redirect:customerhomepage";
            } else {
                return "redirect:subscriptionpage";
            }
        } else {
            // Redirect to login page with error message if authentication fails
            return "redirect:login?error=authentication failed";
        }
    }


}