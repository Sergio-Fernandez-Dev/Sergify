package com.sergify.backend.payload.request;

import java.util.List;

import com.sergify.backend.model.Artist;
import com.sergify.backend.model.Track;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumRequest {
    private String title;
    private String cover;
    private List<Track> trackList;
    private List<Artist> artists;
}
