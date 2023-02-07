package com.sergify.backend.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "artists")
    @JsonIgnore
    @Builder.Default
    private Set<Album> discography = new HashSet<>();

    public void addAlbum(Album album) {
        this.discography.add(album);
    }
}
