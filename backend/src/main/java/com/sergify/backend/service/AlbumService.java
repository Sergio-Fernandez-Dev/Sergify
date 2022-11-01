package com.sergify.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergify.backend.model.Album;
import com.sergify.backend.payload.request.AlbumRequest;
import com.sergify.backend.repository.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
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

    public Album update(Album album) {
        if (albumRepository.findById(album.getId()).isEmpty()) {
            throw new RuntimeException("El álbum solicitado no ha sido encontrado");
        }
        Album response = albumRepository.save(album);

        return response;
    }

    public Long destroy(Long id) {
        if (albumRepository.findById(id).isEmpty()) {
            throw new RuntimeException("El álbum solicitado no ha sido encontrado");
        }

        albumRepository.deleteById(id);
        return id;
    }
}
