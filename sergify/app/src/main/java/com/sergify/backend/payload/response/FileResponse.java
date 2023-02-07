package com.sergify.backend.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponse {
    private String artist;
    private String album;
    private String title;
    private String position;
    private byte[] cover;

}
