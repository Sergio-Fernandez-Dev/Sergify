package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import com.sergify.backend.entity.Album;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(
            value = "SELECT album.id, album.title, album.cover " +
                    "FROM album " +
                    "INNER JOIN album_artist " +
                    "ON album.id = album_artist.album_id " +
                    "INNER JOIN artist " +
                    "ON album_artist.artist_id = artist.id " +
                    "WHERE artist.name = ?1",
            nativeQuery = true
    )
    Optional<Album> findAlbumByArtistName(String artistName);

    @Query(
            value = "SELECT album_id " +
                    "FROM album_artist " +
                    "WHERE artist_id = ?1",
            nativeQuery = true
    )
    Optional<Long> findAlbumIdByArtistId(Long artistId);

}
