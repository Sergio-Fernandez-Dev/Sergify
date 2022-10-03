package com.sergify.backend.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
public class ArtistRequest {
    private Long id;
    private String name;
}
