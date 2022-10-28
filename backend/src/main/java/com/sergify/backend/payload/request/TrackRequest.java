package com.sergify.backend.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
public class TrackRequest {
    
    private Long id;
    private String title;
    private Integer position;
    private String url;
    private Long albumId;    
}