package com.sergify.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    public Optional<Album> show(String artistName) {
        return albumRepository.findByArtistName(artistName);
    }

    public Album store(AlbumRequest request) {
        Album album = Album
                .builder()
                .title(request.getTitle())
                .cover(request.getCover())
                .build();
        addArtistsToAlbum(request.getArtists(), album);
        addTrackListToAlbum(request.getTrackList(), album);

        return albumRepository.save(album);
    }

    public Album update(Album album) {
        if (albumRepository.findById(album.getId()).isEmpty()) {
            throw new RuntimeException("El álbum solicitado no ha sido encontrado");
        }

        return albumRepository.save(album);
    }

    public Long destroy(Long id) {
        if (albumRepository.findById(id).isEmpty()) {
            throw new RuntimeException("El álbum solicitado no ha sido encontrado");
        }

        albumRepository.deleteById(id);
        return id;
    }

    private void addArtistsToAlbum(List<ArtistRequest> request, Album album) {
        Set<Artist> artistList = new HashSet<>();

        for (ArtistRequest currentArtist :request) {
            Artist artist = Artist
                    .builder()
                    .name(currentArtist.getName())
                    .discography(new HashSet<>())
                    .build();
            artistList.add(artist);
            artist.addAlbum(album);
        }
        album.setArtists(artistList);
    }

    private void addTrackListToAlbum(List<TrackRequest> request, Album album) {
        Set<Track> trackList = new HashSet<>();

        for (TrackRequest currentTrack : request) {
            Track track = Track
                    .builder()
                    .title(currentTrack.getTitle())
                    .position(currentTrack.getPosition())
                    .url(currentTrack.getUrl())
                    .build();
            trackList.add(track);
            album.addTrack(track);
        }
        album.setTrackList(trackList);
    }

    public boolean checkIfArtistAlreadyExists(String artistName, Album album) {
        Set<Artist> artists = album.getArtists();

        for (Artist artist : artists) {
            if (artist.getName().equals(artistName)) {
                return true;
            }
        }

        return false;
    }
}
