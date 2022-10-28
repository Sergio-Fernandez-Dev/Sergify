package com.sergify.backend.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String cover;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private final List<Track> trackList = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
        name = "album_artist",
        joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    private final List<Artist> artists = new ArrayList<>();
}
