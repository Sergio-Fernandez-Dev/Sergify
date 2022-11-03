package com.sergify.backend.payload.request;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumRequest {
    private String title;
    private String cover;
    private List<TrackRequest> trackList;
    private List<ArtistRequest> artists;
}
