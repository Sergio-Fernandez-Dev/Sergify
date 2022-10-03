package com.sergify.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.sergify.backend.model.Album;
import com.sergify.backend.payload.request.AlbumRequest;
import com.sergify.backend.repository.IAlbumRepository;

@Service
public class AlbumService {

    private final IAlbumRepository albumRepository;

    @Autowired
    public AlbumService(IAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> index() {

        return albumRepository.findAll();
    }

    public Optional<Album> show(Long id) {
        return albumRepository.findById(id);
    }

    public Album store(AlbumRequest request) {
        Album album = Album
                .builder()
                .title(request.getTitle())
                .cover(request.getCover())
                .build();
        Album response = albumRepository.save(album);

        return response;
    }

    
}
