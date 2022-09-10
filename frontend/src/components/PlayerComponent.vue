<script setup>
import { getAllTracks } from "../services/apiCall";
import { onMounted, ref } from "vue";
import { usePlaylistStore } from "../stores/playlistStore";
import { useRoute } from "vue-router";
import TrackComponent from "./TrackComponent.vue";
import BackwardIcon from "./icons/BackwardIcon.vue";
import PauseIcon from "./icons/PauseIcon.vue";
import ForwardIcon from "./icons/ForwardIcon.vue";
import PlayTrackIcon from "./icons/PlayTrackIcon.vue";

const albumId = useRoute().params.albumId;
const store = usePlaylistStore();

let audio = null;
let duration = null;
let currentTime = null;
let backgroundImg = ref();

onMounted(async () => {
  const album = await getAllTracks(albumId);
  audio = new Audio();
  backgroundImg.value = `url('${album.cover}')`;
  store.addAlbumToPlaylist(album);
  audio.src = store.currentTrack.url;

  audio.ontimeupdate = () => {
    updateProgress();
  };

  audio.onloadedmetadata = () => {
    updateProgress();
  };

  audio.onended = () => {
    nextSong();
  };
});

function startSong() {
  audio.play();
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
  startSong();
}

function previousSong() {
  const playlist = store.playlist;
  let previousSong = store.currentTrack.order - 2;

  if (previousSong < 0) {
    previousSong = playlist.tracks.length - 1;
  }

  store.loadTrack(playlist.tracks[previousSong]);
  audio.src = store.currentTrack.url;
  startSong();
}

function updateProgress() {
  const trackDuration = document.getElementById("track-duration");
  const time = document.getElementById("current-time");

  let durationInMinutes = Math.floor(audio.duration / 60);
  let durationInSeconds = Math.floor(audio.duration - durationInMinutes * 60);
  let currentTimeInMinutes = Math.floor(audio.currentTime / 60);
  let currentTimeInSeconds = Math.floor(
    audio.currentTime - currentTimeInMinutes * 60
  );
  if (durationInMinutes < 10) {
    durationInMinutes = "0" + durationInMinutes;
  }
  if (durationInSeconds < 10) {
    durationInSeconds = "0" + durationInSeconds;
  }
  if (currentTimeInMinutes < 10) {
    currentTimeInMinutes = "0" + currentTimeInMinutes;
  }
  if (currentTimeInSeconds < 10) {
    currentTimeInSeconds = "0" + currentTimeInSeconds;
  }
  duration = durationInMinutes + ":" + durationInSeconds;
  currentTime = currentTimeInMinutes + ":" + currentTimeInSeconds;
  trackDuration.innerText = duration;
  time.innerText = currentTime;
}
</script>

<template>
  <section class="player">
    <div class="player__wrapper">
      <img :src="store.playlist.cover" alt="artist" class="player__img" />
      <div class="player__info">
        <h3 class="player__title">{{ store.currentTrack.title }}</h3>
        <h4 class="player__subtitle">{{ store.playlist.artist }}</h4>
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
          <span id="current-time"></span> {{ currentTime }} /
          <span id="track-duration"> {{ duration }}</span>
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

<style lang="scss" scoped>
.media {
  &__icon {
    width: 3rem;
    fill: map-get($color, "icon-light");
    @include screen($screen-md) {
      width: 5rem;
    }
  }
  &__icons {
    width: 100vw;
    @include flex(row, $justify: space-evenly);
  }
  &__time-info {
  }
  &__timebar {
    &--progress {
    }
  }
}
.player {
  width: 100vw;
  height: 100vh;
  background-color: map-get($map: $color, $key: "background-dark");

  &__img {
    max-width: 90vw;
    margin-top: 3rem;
    border-radius: 100%;
    border: 0.1rem solid #9e9e9e;
    -moz-box-shadow: 0 0 1rem #ffffff;
    -webkit-box-shadow: 0 0 1rem #ffffff;
    box-shadow: 0 0 1.2rem #9e9e9e;
  }
  &__info {
    margin: 2rem auto;
    text-shadow: 0 0.4rem 0.2rem map-get($map: $color, $key: "shadow-dark");
    text-align: center;
  }
  &__title {
    @include font(
      $family: $primary-font-family,
      $size: map-get($map: $font-size, $key: "mv-lg"),
    );
  }
  &__subtitle {
    @include font(
      $family: $primary-font-family,
      $size: map-get($map: $font-size, $key: "mv-md"),
      $style: italic,
      $weight: 300
    );
  }
  &__wrapper {
    position: relative;
    width: 100vw;
    z-index: 1;
    @include flex(column, $justify: space-evenly);

    &::before {
      content: "";
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      opacity: 0.6;
      z-index: -1;
      background-image: v-bind(backgroundImg);
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
      filter: blur(0.75rem);
      -webkit-filter: blur(0.75rem);
    }
  }
}
</style>
