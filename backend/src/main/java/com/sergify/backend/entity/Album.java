package com.sergify.backend.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


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

    @OneToMany(
            mappedBy = "album",
            cascade = {CascadeType.ALL}
    )
    private Set<Track> trackList = new HashSet<>();

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
            }
    )
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artists = new HashSet<>();


    public void addTrack(Track track) {
        track.setAlbum(this);
    }
}
