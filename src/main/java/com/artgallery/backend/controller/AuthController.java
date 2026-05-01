package com.artgallery.backend.controller;

import com.artgallery.backend.model.User;
import com.artgallery.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.artgallery.backend.config.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://fsad-virtual-art-gallery.vercel.app/")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // 🔹 SIGNUP (IMPROVED)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {

        // ❗ Check if user already exists
        if (repo.findByEmail(user.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User already exists");
        }

        // 🔐 Encrypt password
        user.setPassword(encoder.encode(user.getPassword()));

        // Save user
        User savedUser = repo.save(user);

        return ResponseEntity.ok(savedUser);
    }
    @Autowired
    private JwtUtil jwtUtil;

    // 🔹 LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existing = repo.findByEmail(user.getEmail());

        if (existing != null && encoder.matches(user.getPassword(), existing.getPassword())) {

            String token = jwtUtil.generateToken(existing.getEmail(), existing.getRole());

            return ResponseEntity.ok(
                    java.util.Map.of(
                            "token", token,
                            "role", existing.getRole(),
                            "name", existing.getName()
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    //commit1
}