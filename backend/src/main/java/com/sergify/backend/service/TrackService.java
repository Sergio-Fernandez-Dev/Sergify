package com.sergify.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergify.backend.model.Track;
import com.sergify.backend.payload.request.TrackRequest;
import com.sergify.backend.repository.TrackRepository;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> index() {

        return trackRepository.findAll();
    }

    public Optional<Track> show(Long id) {

        return trackRepository.findById(id);
    }

    public Track store(TrackRequest request) {
        Track track = new Track();
        BeanUtils.copyProperties(request, track);

        return trackRepository.save(track);
    }

    public Track update(Track track) {
        if (trackRepository.findById(track.getId()).isEmpty()) {
            throw new RuntimeException("El álbum solicitado no ha sido encontrado");
        }

        return trackRepository.save(track);
    }

    public Long destroy(Long id) {
        if (trackRepository.findById(id).isEmpty()) {
            throw new RuntimeException("El álbum solicitado no ha sido encontrado");
        }

        trackRepository.deleteById(id);
        return id;
    }

}
