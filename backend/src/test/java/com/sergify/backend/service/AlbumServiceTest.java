package com.sergify.backend.service;

import com.sergify.backend.entity.Album;
import com.sergify.backend.payload.AlbumRequest;
import com.sergify.backend.payload.ArtistRequest;
import com.sergify.backend.payload.TrackRequest;
import com.sergify.backend.repository.AlbumRepository;
import com.sergify.backend.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {
    private AlbumService underTestSubject;
    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    void setUp() {
        underTestSubject = new AlbumService(albumRepository, artistRepository);
    }

    @Test
    void testIndex() {
        //given
        //when
        underTestSubject.index();
        //then
        verify(albumRepository).findAll();
    }

    @Test
    @Disabled
    void testShowByAlbumId() {
    }

    @Test
    @Disabled
    void testShowByArtistName() {
    }

    @Test
    void testStore() {
        //given
        AlbumRequest albumRequest = AlbumRequest
                .builder()
                .title("title")
                .cover("cover")
                .build();
        List<ArtistRequest> artists = new ArrayList<>();
        ArtistRequest artistRequest = ArtistRequest
                .builder()
                .name("name")
                .build();
        artists.add(artistRequest);
        albumRequest.setArtists(artists);

        List<TrackRequest> tracks = new ArrayList<>();
        TrackRequest trackRequest = TrackRequest
                .builder()
                .title("title")
                .url("url")
                .position(1)
                .album(albumRequest)
                .build();

        tracks.add(trackRequest);
        albumRequest.setTrackList(tracks);

        //when
        Album album = underTestSubject.store(albumRequest);

        //then
        ArgumentCaptor<Album> albumArgumentCaptor = ArgumentCaptor.forClass(Album.class);
        verify(albumRepository).save(albumArgumentCaptor.capture());
        Album capturedAlbum = albumArgumentCaptor.getValue();

        assertThat(capturedAlbum).isInstanceOf(Album.class);

    }

    @Test
    @Disabled
    void testUpdate() {
    }

    @Test
    @Disabled
    void testDestroy() {
    }
}