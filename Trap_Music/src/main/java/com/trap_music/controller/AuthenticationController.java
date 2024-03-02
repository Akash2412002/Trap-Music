package com.trap_music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trap_music.entity.User;
import com.trap_music.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, HttpSession session) {
        boolean userExists = userService.emailExists(user.getEmail());
        if (!userExists) {
            userService.addUser(user);
            session.setAttribute("email", user.getEmail());
            if (user.getRole().equals("admin")) {
                return "redirect:/adminhomepage";
            } else {
                return "redirect:/customerhomepage";
            }
        } else {
            // Redirect to login page with error message if user already exists
            return "redirect:/login?error=/user already exists";
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
        if (userService.validateUser(email, password)) {
            session.setAttribute("email", email);
            if (userService.getRole(email).equals("admin")) {
                return "redirect:/adminhomepage";
            } else {
                return "redirect:/customerhomepage";
            }
        } else {
            // Redirect to login page with error message if authentication fails
            return "redirect:/login?error=/authentication failed";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate(); // Invalidate session on logout
        return "redirect:/index"; // Redirect to index page after logout
    }
}