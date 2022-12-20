package com.sergify.backend.model;

import lombok.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer position;
    @Column(nullable = false)
    private String url;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;
}
