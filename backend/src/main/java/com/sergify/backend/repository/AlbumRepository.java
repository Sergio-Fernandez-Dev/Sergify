package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergify.backend.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
