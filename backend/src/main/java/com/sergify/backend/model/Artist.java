package com.sergify.backend.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(
        name = "album_artist",
        joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id")
    )
    private final List<Album> discography = new ArrayList<>();
}
