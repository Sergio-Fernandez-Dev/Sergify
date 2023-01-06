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
import MoreOptionsIcon from "./icons/MoreOptionsIcon.vue";
import HomeIcon from "./icons/HomeIcon.vue";

const albumId = useRoute().params.albumId;
const store = usePlaylistStore();
const backgroundImg = ref();
const timeBarWidth = ref(0);
const circleLeft = ref(0);

let audio = null;
let duration = null;
let currentTime = null;

onMounted(async () => {
  const album = await getAllTracks(albumId);
  backgroundImg.value = `url('${album.cover}')`;
  store.addAlbumToPlaylist(album);
  audio = store.audio;
  audio.ontimeupdate = () => {
    updateTime();
  };

  audio.onloadedmetadata = () => {
    updateTime();
  };

  audio.onended = () => {
    store.nextSong();
  };
});

function updateTime() {
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

function moveTimeBar(e) {
  updateTimeBar(e.pageX);
}

function updateTimeBar(currentPosition) {
  let progress = ref();
  let maxDuration = this.audio.duration;
  let position = currentPosition - progress.value.offsetLeft;
  let percentage = (100 * position) / progress.value.offsetWidth;
  if (percentage > 100) {
    percentage = 100;
  }
  if (percentage < 0) {
    percentage = 0;
  }
  timeBarWidth.value = percentage + "%";
  circleLeft.value = percentage + "%";
  audio.currentTime = (maxDuration * percentage) / 100;
  audio.play();
}
</script>

<template>
  <section class="player">
    <div class="display">
      <img class="display__img" :src="store.playlist.cover" alt="artist" />
      <div class="display__info">
        <h3 class="display__title">{{ store.currentTrack.title }}</h3>
        <h4 class="display__subtitle">{{ store.playlist.artist }}</h4>
      </div>
      <div class="display__icons">
        <MoreOptionsIcon class="display__icon" />
        <RouterLink to="/">
          <HomeIcon class="display__icon" />
        </RouterLink>
      </div>
    </div>
    <div class="media">
      <div class="media__icons">
        <BackwardIcon class="media__icon" @click="store.previousSong()" />
        <PlayTrackIcon class="media__icon" @click="store.startSong()" />
        <PauseIcon class="media__icon" @click="store.pauseSong()" />
        <ForwardIcon class="media__icon" @click="store.nextSong()" />
      </div>
      <div class="media__wrapper">
        <p class="media__time-info">
          <span id="current-time"></span> {{ currentTime }} /
          <span id="track-duration"> {{ duration }}</span>
        </p>
        <div class="media__timebar" @click="moveTimeBar(e)">
          <div
            class="media__timebar--progress"
            :style="{ width: timeBarWidth }"
          ></div>
        </div>
      </div>
    </div>
    <div class="playlist">
      <ul class="playlist__ul">
        <TrackComponent
          v-for="(track, index) in store.playlist.tracks"
          :key="index + 1"
          :track="track"
        />
      </ul>
    </div>
  </section>
</template>

<style lang="scss" scoped>
.player {
  width: 100%;
  height: 100%;
  background-color: map-get($map: $color, $key: "background-dark");
  .display {
    position: relative;
    width: 100%;
    z-index: 1;
    @include flex(column);

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
    &__icons {
      width: 90%;
      margin-bottom: 1.2rem;
      @include flex(row, $justify: space-between);
    }
    &__icon {
      text-shadow: 0 0 0.3rem map-get($map: $color, $key: "shadow-dark");
    }
    &__img {
      width: 85%;
      margin-top: 4.5rem;
      border-radius: 100%;
      border: 0.1rem solid map-get($map: $color, $key: "border-light");
      -moz-box-shadow: 0 0 1rem map-get($map: $color, $key: "shadow-light");
      -webkit-box-shadow: 0 0 1rem map-get($map: $color, $key: "shadow-light");
      box-shadow: 0 0 1.2rem map-get($map: $color, $key: "shadow-light");
    }
    &__info {
      max-width: 90%;
      margin-top: 2rem;
      text-shadow: 0 0 0.3rem map-get($map: $color, $key: "shadow-dark");
      text-align: center;
    }
    &__title {
      @include font(
        $family: $primary-font-family,
        $size: map-get($map: $font-size, $key: "mv-lg")
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
  }
  .media {
    width: 100%;
    height: 12rem;
    border-top: 0.1rem solid map-get($map: $color, $key: "border-light");
    background-color: map-get($map: $color, $key: "primary-dark");
    z-index: 1;
    @include flex(column, $justify: space-evenly);
    &__icon {
      width: 3rem;
      fill: map-get($color, "icon-light");
      @include screen($screen-md) {
        width: 5rem;
      }
    }
    &__icons {
      width: 90vw;
      @include flex(row, $justify: space-between);
    }
    &__time-info {
      @include font(
        $family: $primary-font-family,
        $size: map-get($map: $font-size, $key: "mv-xs")
      );
      padding-bottom: 0.5rem;
    }
    &__timebar {
      width: 100%;
      padding: 0;
      margin: 0;
      height: 0.5rem;
      border: 0.1rem solid map-get($map: $color, $key: "timebar-border");
      background-color: map-get($map: $color, $key: "timebar");
      @include flex(row, $justify: flex-start);
      &--progress {
        margin: -0.1rem;
        width: 30%;
        height: 0.5rem;
        background-color: map-get($map: $color, $key: "timebar-progress");
      }
    }
    &__wrapper {
      width: 90%;
    }
  }
  .playlist {
    background-color: map-get($map: $color, $key: "secondary");

    &__ul {
      @include flex(column);
    }
  }
}
</style>
