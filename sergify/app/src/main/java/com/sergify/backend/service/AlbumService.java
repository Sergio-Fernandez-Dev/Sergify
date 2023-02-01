package com.sergify.backend.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.sergify.backend.entity.Album;
import com.sergify.backend.entity.Artist;
import com.sergify.backend.entity.Track;
import com.sergify.backend.payload.AlbumRequest;
import com.sergify.backend.payload.ArtistRequest;
import com.sergify.backend.payload.TrackRequest;
import com.sergify.backend.repository.AlbumRepository;
import com.sergify.backend.repository.ArtistRepository;


@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public List<Album> index() {

        return albumRepository.findAll();
    }

    public Optional<Album> show(Long id) {

        return albumRepository.findById(id);
    }

    public Optional<Album> show(String artistName) {
        return albumRepository.findAlbumByArtistName(artistName);
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

    public Album update(Long id, AlbumRequest request) {
        Album album = albumRepository.findById(id).orElseThrow();

        BeanUtils.copyProperties(request, album);

        addArtistsToAlbum(request.getArtists(), album);
        addTrackListToAlbum(request.getTrackList(), album);

        return albumRepository.save(album);
    }

    public Long destroy(Long id) {
        if (albumRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("El álbum solicitado no ha sido encontrado");
        }
        Long artistId = artistRepository.findArtistIdByAlbumId(id).orElseThrow();
        albumRepository.deleteById(id);
        this.removeArtistWithoutAlbum(artistId);

        return id;
    }

    private void addArtistsToAlbum(List<ArtistRequest> request, Album album) {
        Set<Artist> artistList = new HashSet<>();

        for (ArtistRequest currentArtist : request) {
            Artist artist = artistRepository
                    .findByNameIgnoreCase(currentArtist.getName())
                    .orElseGet(() -> {
                        Artist newArtist = new Artist();
                        BeanUtils.copyProperties(currentArtist, newArtist);
                        return newArtist;
                    });

            artistList.add(artist);
            artist.addAlbum(album);
        }
        album.setArtists(artistList);
    }

    private void addTrackListToAlbum(List<TrackRequest> request, Album album) {
        Set<Track> trackList = new HashSet<>();

        for (TrackRequest currentTrack : request) {
            Track track = new Track();
            BeanUtils.copyProperties(currentTrack, track);
            trackList.add(track);
            album.addTrack(track);
        }
        album.setTrackList(trackList);
    }

    private void removeArtistWithoutAlbum(Long artistId) {
        if (albumRepository.findAlbumIdByArtistId(artistId).isEmpty()) {
            artistRepository.deleteById(artistId);
        }
    }
}
