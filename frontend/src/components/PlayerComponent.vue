<template>
  <section class="player">
    <div class="player__wrapper">
      <img :src="store.playlist.cover" alt="artist" class="player__img" />
      <div class="player__info">
        <h3 class="title-big">{{ store.playlist.artist }}</h3>
        <h4 class="subtitle-big">{{ store.currentTrack.title }}</h4>
      </div>
    </div>
    <div class="media">
      <div class="media__icons">
        <BackwardIcon class="media__icon" @click="previousSong()" />
        <PlayTrackIcon class="media__icon" @click="startSong()" />
        <PauseIcon class="media__icon" @click="pauseSong()" />
        <ForwardIcon class="media__icon" @click="nextSong()" />
      </div>
      <div class="media__wrapper">
        <p class="media__time-info">
          <span id="current-time"></span> {{audio.currentTime}} / <span id="track-duration"> {{ audio.duration }}</span>
        </p>
        <div class="media__timebar">
          <div class="media__timebar--progress"></div>
        </div>
      </div>
    </div>
    <div class="playlist">
      <ul class="playlist__ul">
        <li class="playlist__li">
          <TrackComponent
            v-for="(track, index) in store.playlist.tracks"
            :key="index + 1"
            :order="track.order"
            :title="track.title"
          />
        </li>
      </ul>
    </div>
  </section>
</template>

<script setup>
import { getAllTracks } from "../services/apiCall";
import { onMounted } from "vue";
import { usePlaylistStore } from "../stores/playlistStore";
import { useRoute } from "vue-router";
import TrackComponent from "./TrackComponent.vue";
import BackwardIcon from "./icons/BackwardIcon.vue";
import PauseIcon from "./icons/PauseIcon.vue";
import ForwardIcon from "./icons/ForwardIcon.vue";
import PlayTrackIcon from "./icons/PlayTrackIcon.vue";

const albumId = useRoute().params.albumId;
const store = usePlaylistStore();
const audio = new Audio();

onMounted(async () => {
  const data = await getAllTracks(albumId);
  store.addAlbumToPlaylist(data);
  audio.src = store.currentTrack.url;
  audio.load();
});

function startSong() {
  audio.play();
  console.log(audio.src);
}

function pauseSong() {
  audio.pause();
}

function nextSong() {
  const playlist = store.playlist;
  let nextSong = store.currentTrack.order;

  if (nextSong > playlist.tracks.length - 1) {
    nextSong = 0;
  }

  store.loadTrack(playlist.tracks[nextSong]);
  audio.src = store.currentTrack.url;
  audio.load();
  startSong();
}

function previousSong() {
  const playlist = store.playlist;
  let previousSong = store.currentTrack.order - 2;

  if (previousSong < 0) {
    previousSong = playlist.tracks.length - 1;
  }

  store.loadTrack(playlist.tracks[previousSong]);
  startSong();
}

function updateProgress(event) {
  const { duration, currentTime } = event.srcElement;
  const trackDuration = document.getElementById("track-duration");
  const time = document.getElementById("current-time");

  trackDuration.innerText = duration;
  time.innerText = currentTime;
}
</script>

<style lang="scss" scoped>
@import "@/assets/scss/components/player";
</style>
