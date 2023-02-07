package com.sergify.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.sergify.backend.entity.Artist;
import com.sergify.backend.payload.request.ArtistRequest;
import com.sergify.backend.repository.ArtistRepository;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> index() {

        return artistRepository.findAll();
    }

    public Optional<Artist> show(Long id) {
        return artistRepository.findById(id);
    }

    public Artist store(ArtistRequest request) {
        Artist artist = new Artist();
        BeanUtils.copyProperties(request, artist);

        return artistRepository.save(artist);
    }

    public Artist update(Artist artist) {
        if (artistRepository.findById(artist.getId()).isEmpty()) {
            throw new RuntimeException("El artista solicitado no ha sido encontrado");
        }

        return artistRepository.save(artist);
    }

    public Long destroy(Long id) {
        if (artistRepository.findById(id).isEmpty()) {
            throw new RuntimeException("El artista solicitado no ha sido encontrado");
        }
        artistRepository.deleteById(id);

        return id;
    }
}