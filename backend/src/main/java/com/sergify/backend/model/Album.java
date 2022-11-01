package com.sergify.backend.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false, unique = true)
    private String title;
    private String cover;
    @OneToMany(mappedBy = "album")
    private Set<Track> trackList = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "album_artist", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new HashSet<>();

    public void addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getDiscography().add(this);
    }

    public void removeArtist(Long artistId) {
        Artist artist = this.artists
                .stream()
                .filter(currentArtist -> currentArtist.getId() == artistId)
                .findFirst()
                .orElse(null);

        if (artist != null) {
            this.artists.remove(artist);
            artist.getDiscography().remove(this);
        }
    }
}
