import { defineStore } from "pinia";
import { ref } from "vue";

export const usePlaylistStore = defineStore("playlist", () => {
  const playlist = ref({});
  const currentTrack = ref({});

  function addAlbumToPlaylist(album) {
    const tracklist = [];
    album.tracks.forEach((track) => {
      tracklist.push({
        order: track.order,
        title: track.title,
        url: track.url,
      });
    });
    this.playlist = {
      artist: album.artist.name,
      cover: album.cover,
      tracks: tracklist,
    };

    this.loadTrack(tracklist[0]);

  }

  function loadTrack(track) {
    this.currentTrack = track;
  }

  return { playlist, currentTrack, addAlbumToPlaylist, loadTrack };
});
