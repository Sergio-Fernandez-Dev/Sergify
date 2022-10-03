package com.sergify.backend.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
public class AlbumRequest {
    private Long id;
    private String title;
    private String cover;
}
