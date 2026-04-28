package com.artgallery.backend.controller;

import com.artgallery.backend.model.User;
import com.artgallery.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository repo;

    // 🔹 GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // 🔹 DELETE USER
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
        return "User deleted";
    }
}