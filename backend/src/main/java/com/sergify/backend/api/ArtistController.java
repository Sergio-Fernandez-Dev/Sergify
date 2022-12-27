package com.sergify.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sergify.backend.entity.Artist;
import com.sergify.backend.payload.ArtistRequest;
import com.sergify.backend.service.ArtistService;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("")
    public ResponseEntity<List<Artist>> index() {
        List<Artist> response = artistService.index();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Artist>> show(@PathVariable Long id) {
        Optional<Artist> response = artistService.show(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<Artist> store(@RequestBody ArtistRequest request) {
        Artist response = artistService.store(request);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("")
    public ResponseEntity<Artist> update(@RequestBody Artist artist) {
        Artist response = artistService.update(artist);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> destroy(@PathVariable Long id) {
        Long response = artistService.destroy(id);
        return ResponseEntity.ok().body(response);
    }
}