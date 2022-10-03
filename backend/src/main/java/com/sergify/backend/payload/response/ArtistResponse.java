package com.sergify.backend.payload.response;

import org.springframework.beans.factory.annotation.Autowired;

import com.sergify.backend.model.Artist;

import lombok.*;

@Getter
@Setter
@Builder
public class ArtistResponse {
    private Artist artist;    

    @Autowired
    public ArtistResponse(final Artist artist) {
        this.artist = artist;
    }
}
