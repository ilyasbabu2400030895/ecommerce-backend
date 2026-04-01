package com.klu.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.User;
import com.klu.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Signup
    @PostMapping("/signup")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login
    @Autowired
private UserRepository userRepository;

@Autowired
private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

public User login(String email, String password) {

    User user = userRepository.findByEmail(email);

    if (user == null) {
        throw new RuntimeException("User not found");
    }

    // 🔥 MAIN FIX
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid login credentials");
    }

    return user;
}
}
