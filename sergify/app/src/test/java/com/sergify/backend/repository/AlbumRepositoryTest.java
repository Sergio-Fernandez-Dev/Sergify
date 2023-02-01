package com.sergify.backend.repository;

import com.sergify.backend.entity.Album;
import com.sergify.backend.entity.Artist;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlbumRepositoryTest {
    @Autowired
    AlbumRepository underTestSubject;

    @AfterEach
    void tearDown() {
        underTestSubject.deleteAll();
    }
    
    @Test()
    @Order(3)
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
    @Order(4)
    void itShouldReturnEmptyValueWhenNonExistentArtistNameIsGiven() {
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
        Optional<Album> expected = underTestSubject.findAlbumByArtistName("Undefined Artist");

        //Then
        assertThat(expected).isEmpty();
    }

    @Test
    @Order(1)
    void itShouldReturnAlbumIdWhenArtistIdIsGiven() {
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
        Album currentAlbum = underTestSubject.save(album);
        Optional<Long> expected = underTestSubject.findAlbumIdByArtistId(1L);

        //Then
        assertThat(currentAlbum.getId()).isEqualTo(expected.get());
    }

    @Test
    @Order(2)
    void itShouldReturnEmptyValueWhenNonExistentArtistIdIsGiven() {
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
        Album currentAlbum = underTestSubject.save(album);
        Optional<Long> expected = underTestSubject.findAlbumIdByArtistId(300L);

        //Then
        assertThat(expected).isEmpty();
    }
}