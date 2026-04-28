package com.artgallery.backend.controller;

import com.artgallery.backend.model.OrderEntity;
import com.artgallery.backend.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    // 🔹 GET all orders
    @GetMapping
    public List<OrderEntity> getAll() {
        return repo.findAll();
    }

    // 🔹 CREATE order
    @PostMapping
    public OrderEntity create(@RequestBody OrderEntity order) {
        return repo.save(order);
    }
    @PutMapping("/{id}/status")
    public OrderEntity updateStatus(@PathVariable Long id, @RequestBody OrderEntity updated) {
        OrderEntity order = repo.findById(id).orElseThrow();
        order.setStatus(updated.getStatus());
        return repo.save(order);
    }
}