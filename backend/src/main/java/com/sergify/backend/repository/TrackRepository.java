package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergify.backend.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

}
