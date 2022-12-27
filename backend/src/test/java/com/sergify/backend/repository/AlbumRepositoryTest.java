package com.sergify.backend.repository;

import com.sergify.backend.entity.Album;
import com.sergify.backend.entity.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AlbumRepositoryTest {
    @Autowired
    AlbumRepository underTestSubject;

    @AfterEach
    void tearDown() {
        underTestSubject.deleteAll();
    }
    
    @Test
    void itShouldReturnAlbumWhenArtistNameIsGiven() {
        //Given
        Album album = Album
                .builder()
                .title("title")
                .cover("cover")
                .build();
        Artist artist = Artist
                .builder()
                .name("Artist")
                .build();
        
        Set<Artist> artists = new HashSet<>();
        artists.add(artist);
        album.setArtists(artists);

        //When
        underTestSubject.save(album);
        Optional<Album> expected = underTestSubject.findAlbumByArtistName(artist.getName());

        //Then
        assertThat(expected).containsInstanceOf(Album.class);
    }

    @Test
    void itShouldReturnAlbumIdWhenArtistIdIsGiven() {
        //Given
        Album album = Album
                .builder()
                .id(10L)
                .title("title")
                .cover("cover")
                .build();
        Artist artist = Artist
                .builder()
                .id(1L)
                .name("Artist")
                .build();

        Set<Artist> artists = new HashSet<>();
        artists.add(artist);
        album.setArtists(artists);

        //When
        Album currentAlbum = underTestSubject.save(album);
        Optional<Long> expected = underTestSubject.findAlbumIdByArtistId(artist.getId());

        //Then
        assertThat(currentAlbum.getId()).isEqualTo(expected.get());
    }
}