package com.sergify.backend.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sergify.backend.entity.Album;
import com.sergify.backend.payload.request.AlbumRequest;
import com.sergify.backend.payload.response.FileResponse;
import com.sergify.backend.service.AlbumService;
import com.sergify.backend.service.FileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    private final AlbumService albumService;
    private final FileService fileService;

    public AlbumController(AlbumService albumService, FileService fileService) {
        this.albumService = albumService;
        this.fileService = fileService;
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

    @PostMapping("/upload")
    public ResponseEntity<Set<FileResponse>> upload(@RequestParam("files") MultipartFile[] multipartFiles) {
        try {    
            Set<FileResponse> response = fileService.getMetadata(multipartFiles);
                      
            return ResponseEntity.ok().body(response); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }    
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