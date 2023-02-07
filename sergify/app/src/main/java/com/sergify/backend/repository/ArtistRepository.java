package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sergify.backend.entity.Artist;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameIgnoreCase(String name);

    @Query(
            value = "SELECT artist_id " +
                    "FROM album_artist " +
                    "WHERE album_id = ?1",
            nativeQuery = true
    )
    Optional<Long> findArtistIdByAlbumId(Long albumId);
}
