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

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        List<Album> albums = underTestSubject.index();
        //then
        verify(albumRepository).findAll();
        assertThat(albums).isInstanceOf(List.class);
    }

    @Test
    @Disabled
    void testShowByAlbumId() {
        //given
        Long albumId = new Random().nextLong();
        //when
        underTestSubject.show(albumId);
        //then
        verify(albumRepository).findById(albumId);

    }

    @Test
    @Disabled
    void testShowByArtistName() {
        //given
        String artistName = "Bon Jovi";
        //when
        underTestSubject.show(artistName);
        //then
        verify(albumRepository).findAlbumByArtistName(artistName);
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
        underTestSubject.store(albumRequest);

        //then
        ArgumentCaptor<Album> albumArgumentCaptor = ArgumentCaptor.forClass(Album.class);
        verify(albumRepository).save(albumArgumentCaptor.capture());
        Album capturedAlbum = albumArgumentCaptor.getValue();

        assertThat(capturedAlbum).isInstanceOf(Album.class);
        assertThat(capturedAlbum.getTitle()).isEqualTo(albumRequest.getTitle());
    }

    @Test
    void testUpdateThrowsWhenAlbumDoNotExists() {
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

        Long albumId = new Random().nextLong();
        //when
        //then
        assertThatThrownBy(() -> underTestSubject.update(albumId, albumRequest))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testUpdate() {
        //given
        ArgumentCaptor<Album> albumArgumentCaptor = ArgumentCaptor.forClass(Album.class);
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

        Album album = new Album();
        album.setTitle("mocked");

        //when
        when(albumRepository.findById(anyLong())).thenReturn(Optional.of(album));
        underTestSubject.update(anyLong(), albumRequest);
        verify(albumRepository).save(albumArgumentCaptor.capture());
        //then
        Album albumResponse = albumArgumentCaptor.getValue();
        assertThat(album.getTitle()).isEqualTo(albumResponse.getTitle());
    }
    @Test
    void testDestroy() {
        //given
        Long artistId = new Random().nextLong();
        Long albumId = new Random().nextLong();
        Album album = new Album();
        //when
        when(albumRepository.findById(albumId))
                .thenReturn(Optional.of(album));

        when(artistRepository.findArtistIdByAlbumId(albumId))
                .thenReturn( Optional.of(artistId));

        Long result = underTestSubject.destroy(albumId);
        verify(albumRepository).deleteById(albumId);
        //then
        assertThat(result).isEqualTo(albumId);
    }

    @Test
    public void testDestroyThrowsNoSuchElementExceptionWhenAlbumDoNotExist() throws NoSuchElementException {
        //given
        Long albumId = new Random().nextLong();
        //when
        //then
        assertThatThrownBy(() -> underTestSubject.destroy(albumId))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testDestroyThrowsNoSuchElementExceptionWhenArtistIdDoNotExist() throws NoSuchElementException {
        //given
        Long albumId = new Random().nextLong();
        Album album = new Album();

        //when
        when(albumRepository.findById(albumId))
                .thenReturn(Optional.of(album));

        //then
        assertThatThrownBy(() -> underTestSubject.destroy(albumId))
                .isInstanceOf(NoSuchElementException.class);
    }
}