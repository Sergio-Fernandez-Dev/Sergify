package com.sergify.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergify.backend.model.Album;
import com.sergify.backend.model.Artist;
import com.sergify.backend.model.Track;
import com.sergify.backend.payload.request.AlbumRequest;
import com.sergify.backend.payload.request.ArtistRequest;
import com.sergify.backend.payload.request.TrackRequest;
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
        Set<Artist> artists = new HashSet<>();
        Set<Track> trackList = new HashSet<>();    
        
        for (ArtistRequest currentArtist : request.getArtists()) {
            Artist artist = Artist
                    .builder()
                    .name(currentArtist.getName())
                    .build();
            artists.add(artist);
        }
        Album album = Album
                .builder()
                .title(request.getTitle())
                .cover(request.getCover())
                .artists(artists)
                .build();

        for (TrackRequest currentTrack : request.getTrackList()) {
            Track track = Track
                    .builder()
                    .title(currentTrack.getTitle())
                    .position(currentTrack.getPosition())
                    .url(currentTrack.getUrl())
                    .build();                 
            trackList.add(track);
            album.addTrack(track);
            album.setTrackList(trackList);
        }

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
