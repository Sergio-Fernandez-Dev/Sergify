package com.sergify.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sergify.backend.entity.Track;
import com.sergify.backend.payload.TrackRequest;
import com.sergify.backend.service.TrackService;

@RestController
@RequestMapping("/api/v1/track")

public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("")
    public ResponseEntity<List<Track>> index() {
        List<Track> response = trackService.index();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Track>> show(@PathVariable Long id) {
        Optional<Track> response = trackService.show(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<Track> store(@RequestBody TrackRequest request) {
        Track response = trackService.store(request);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("")
    public ResponseEntity<Track> update(@RequestBody Track track) {
        Track response = trackService.update(track);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> destroy(@PathVariable Long id) {
        Long response = trackService.destroy(id);
        return ResponseEntity.ok().body(response);
    }
    
}
