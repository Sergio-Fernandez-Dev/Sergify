package com.sergify.backend.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumRequest {
    private Long id;
    private String title;
    private String cover;
}
