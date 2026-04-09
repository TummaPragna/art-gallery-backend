package com.artgallery.backend.service;

import com.artgallery.backend.model.Artwork;
import com.artgallery.backend.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//commit3
@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository repo;

    public List<Artwork> getAllArtworks() {
        return repo.findAll();
    }

    public Artwork addArtwork(Artwork art) {
        return repo.save(art);
    }

    public Artwork updateArtwork(Artwork art) {
        return repo.save(art);
    }

    public void deleteArtwork(Long id) {
        repo.deleteById(id);
    }

    public Artwork getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}