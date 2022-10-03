package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergify.backend.model.Artist;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Long> {

}
