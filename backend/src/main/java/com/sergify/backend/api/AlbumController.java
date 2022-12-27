package com.sergify.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sergify.backend.entity.Album;
import com.sergify.backend.payload.AlbumRequest;
import com.sergify.backend.service.AlbumService;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("")
    public ResponseEntity<List<Album>> index() {
        List<Album> response = albumService.index();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Album>> show(@PathVariable Long id) {
        Optional<Album> response = albumService.show(id);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/artist/{artistName}")
    public ResponseEntity<Optional<Album>> show(@PathVariable String artistName) {
        Optional<Album> response = albumService.show(artistName);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<Album> store(@RequestBody AlbumRequest request) {
        Album response = albumService.store(request);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@PathVariable Long id, @RequestBody AlbumRequest request) {
        Album response = albumService.update(id, request);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> destroy(@PathVariable Long id) {
        Long response = albumService.destroy(id);
        return ResponseEntity.ok().body(response);
    }
}