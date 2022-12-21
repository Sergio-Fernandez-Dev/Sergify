package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sergify.backend.model.Album;

import java.util.ArrayList;
import java.util.Optional;

@Repository
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
    Optional<Album> findByArtistName(String artistName);
    Optional<Iterable<String>> findDiscographyByAlbumId(Long albumId);
}
