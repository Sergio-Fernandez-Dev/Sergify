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

<script setup>
import { getAllTracks } from "../services/apiCall";
import { onBeforeMount, onMounted, ref } from "vue";
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

<style lang="scss" scoped>
.media {
  &__icon {
    width: 5rem;
    fill: map-get($color, "icon-light");
  }
  &__icons {
    width: 100vw;
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
  }
  &__info {
  }
  &__wrapper {
    position: relative;
    width: 100vh;
    z-index: 1;

    &::before {
      content: "";
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      opacity: 0.4;
      z-index: -1;
      background-image: v-bind(backgroundImg);
      background-size: cover;
    }
  }
}
</style>
