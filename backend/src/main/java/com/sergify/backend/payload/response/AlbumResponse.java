package com.sergify.backend.payload.response;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sergify.backend.model.Album;
import com.sergify.backend.model.Artist;
import com.sergify.backend.model.Track;

import lombok.*;

@Getter
@Setter
@Builder
public class AlbumResponse {
    private Album album;
    private Artist artist;
    private List<Track> trackList;

    @Autowired
    public AlbumResponse(final Album album, final Artist artist, final List<Track> trackList) {
        this.album = album;
        this.artist = artist;
        this.trackList = trackList;
    }

}
