package com.sergify.backend.payload.request;

import org.springframework.web.multipart.MultipartFile;

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
    private AlbumRequest album;
    private MultipartFile track;
}