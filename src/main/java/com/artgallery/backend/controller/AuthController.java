package com.artgallery.backend.controller;

import com.artgallery.backend.model.User;
import com.artgallery.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder encoder;

    // 🔹 SIGNUP
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
    	user.setPassword(encoder.encode(user.getPassword()));
    	return repo.save(user);
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existing = repo.findByEmail(user.getEmail());

        if (existing != null && encoder.matches(user.getPassword(), existing.getPassword())) {
            return ResponseEntity.ok(existing);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email or password");
    }
}