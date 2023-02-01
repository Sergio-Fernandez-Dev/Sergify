package com.sergify.backend.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistRequest {
    private Long id;
    private String name;
}
