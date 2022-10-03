package com.sergify.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sergify.backend.model.Album;
import com.sergify.backend.payload.request.AlbumRequest;
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
    public List<Album> index() {

        return albumService.index();
    }

    @GetMapping("/{id}")
    public Optional<Album> show(@PathVariable Long id) {

        return albumService.show(id);
    }

    @PostMapping("")
    public ResponseEntity<?> store(@RequestBody AlbumRequest request) {
        Album response = albumService.store(request);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Album album) {
        Album request = albumService.update(id, album);

        return ResponseEntity.ok()
                .body("El álbum '" + request.getTitle() + "' ha sido guardado correctamente");

    }

}
