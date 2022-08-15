import { defineStore } from "pinia";
import { ref } from "vue";

export const usePlaylistStore = defineStore("playist", () => {
  const playlist = ref({});

  function addAlbumToPlaylist(album) {
    const tracklist = [];
    album.tracks.forEach((track) => {
      tracklist.push({
        order: track.order,
        title: track.title,
        url: track.url,
      });
    });
    console.log(tracklist);
    this.playlist = {
      artist: album.artist.name,
      cover: album.cover,
      tracks: tracklist,
    };
    console.log(this.playlist);
  }

  return { playlist, addAlbumToPlaylist };
});
