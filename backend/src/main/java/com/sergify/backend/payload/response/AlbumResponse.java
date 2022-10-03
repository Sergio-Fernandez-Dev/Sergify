package com.sergify.backend.payload.response;

import java.lang.reflect.Array;

import org.springframework.beans.factory.annotation.Autowired;

import com.sergify.backend.model.Album;
import com.sergify.backend.model.Artist;
import com.sergify.backend.model.Track;

import lombok.*;

@Getter
@Setter
@Builder
public class AlbumResponse {
    private final Album album;
    private final Artist artist;
    private final Array trackList;

    @Autowired
    public AlbumResponse(Album album, Artist artist, Array trackList) {
        this.album = album;
        this.artist = artist;
        this.trackList = trackList;
    }

}
