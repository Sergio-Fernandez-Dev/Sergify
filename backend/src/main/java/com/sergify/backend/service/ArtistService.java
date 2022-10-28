package com.sergify.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergify.backend.model.Artist;
import com.sergify.backend.payload.request.ArtistRequest;
import com.sergify.backend.repository.IArtistRepository;

@Service
public class ArtistService {

    private final IArtistRepository artistRepository;

    @Autowired
    public ArtistService(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> index() {

        return artistRepository.findAll();
    }

    public Optional<Artist> show(Long id) {
        return artistRepository.findById(id);
    }

    public Artist store(ArtistRequest request) {
        Artist artist = Artist
                .builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        Artist response = artistRepository.save(artist);

        return response;
    }

    public Artist update(Artist artist) {
        if (artistRepository.findById(artist.getId()).isEmpty()) {
            throw new RuntimeException("El artista solicitado no ha sido encontrado");
        }
        Artist response = artistRepository.save(artist);

        return response;
    }

    public Long destroy(Long id) {
        if (artistRepository.findById(id).isEmpty()) {
            throw new RuntimeException("El artista solicitado no ha sido encontrado");
        }
        artistRepository.deleteById(id);
        
        return id;
    }
}