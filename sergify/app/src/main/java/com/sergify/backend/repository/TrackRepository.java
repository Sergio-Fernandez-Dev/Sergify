package com.sergify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergify.backend.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {

}
