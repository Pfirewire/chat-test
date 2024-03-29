package com.example.chattest.controllers;

import com.example.chattest.models.User;
import com.example.chattest.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    // Repositories and Services
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    // Constructor
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    // Shows form to sign up as a user
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        // Sending empty user to template
        model.addAttribute("user", new User());
        return "user/signup";
    }

    // Saves user to users table
    @PostMapping("/signup")
//    public String saveUser(@ModelAttribute User user, HttpServletRequest httpServletRequest) {
    public String saveUser(@ModelAttribute User user) {
        String plainPassword = user.getPassword();
        // Hashing password
        String hash = passwordEncoder.encode(user.getPassword());
        // Setting user password to the hash and saving user to table
        user.setPassword(hash);
        userDao.save(user);
//        authWithHttpServletRequest(httpServletRequest, user.getUsername(), plainPassword);
        return "messages";
    }

//    private void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
//        try {
//            request.login(username, password);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
//    }
}
