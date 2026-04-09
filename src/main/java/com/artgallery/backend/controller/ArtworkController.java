package com.artgallery.backend.controller;

import com.artgallery.backend.model.Artwork;
import com.artgallery.backend.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//commit2
@RestController
@RequestMapping("/api/artworks")
@CrossOrigin(origins = "*")
public class ArtworkController {

    @Autowired
    private ArtworkService service;

    // GET ALL
    @GetMapping
    public List<Artwork> getAll() {
        return service.getAllArtworks();
    }

    // ADD ARTWORK
    @PostMapping
    public Artwork add(@RequestBody Artwork art) {
        art.setStatus("Pending");
        return service.addArtwork(art);
    }

    // APPROVE
    @PutMapping("/{id}/approve")
    public Artwork approve(@PathVariable Long id) {
        Artwork art = service.getById(id);
        art.setStatus("Approved");
        return service.updateArtwork(art);
    }

    // REJECT
    @PutMapping("/{id}/reject")
    public Artwork reject(@PathVariable Long id) {
        Artwork art = service.getById(id);
        art.setStatus("Rejected");
        return service.updateArtwork(art);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteArtwork(id);
    }
}