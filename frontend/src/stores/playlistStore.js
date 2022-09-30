import { defineStore } from "pinia";
import { ref } from "vue";

export const usePlaylistStore = defineStore("playlist", () => {
  const playlist = ref({});
  const currentTrack = ref({});

  let audio = new Audio();

  function addAlbumToPlaylist(album) {
    const tracklist = [];
    album.tracks.forEach((track) => {
      tracklist.push({
        position: track.position,
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
    this.pauseSong();
    this.currentTrack = track;
    this.audio.src = track.url;
    this.startSong();
  }

  function startSong() {
    this.audio.play();
  }

  function pauseSong() {
    this.audio.pause();
  }

  function nextSong() {
    const playlist = this.playlist;
    let nextSong = this.currentTrack.position;

    if (nextSong > playlist.tracks.length - 1) {
      nextSong = 0;
    }

    this.loadTrack(playlist.tracks[nextSong]);
  }

  function previousSong() {
    const playlist = this.playlist;
    let previousSong = this.currentTrack.position - 2;

    if (previousSong < 0) {
      previousSong = playlist.tracks.length - 1;
    }

    this.loadTrack(playlist.tracks[previousSong]);
  }

  return {
    audio,
    playlist,
    currentTrack,
    addAlbumToPlaylist,
    loadTrack,
    startSong,
    pauseSong,
    nextSong,
    previousSong,
  };
});
