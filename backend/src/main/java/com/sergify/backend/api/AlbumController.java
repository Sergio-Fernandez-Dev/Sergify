package com.sergify.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
    @GetMapping("")
    public String index() {
        return "albums";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id) {
        return "album " + id;
    }

}
