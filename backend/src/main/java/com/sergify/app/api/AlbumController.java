package com.sergify.app.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/album")
public class AlbumController {

    @GetMapping("")
    public String index() {
        return "Album list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        return "Album " + id;
    }
}
