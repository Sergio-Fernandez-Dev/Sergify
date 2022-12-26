package com.sergify.backend.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackRequest {
    private Long id;
    private String title;
    private Integer position;
    private String url;
    private AlbumRequest album;
}