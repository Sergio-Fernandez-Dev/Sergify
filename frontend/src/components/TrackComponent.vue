<script setup>
import { onMounted } from "vue";
import { usePlaylistStore } from "../stores/playlistStore";
import PlayTrackIcon from "./icons/PlayTrackIcon.vue";

const props = defineProps({ track: Object });
const store = usePlaylistStore();

let audio = null;

onMounted(async () => {
  audio = new Audio();
  audio.src = props.track.url;

});

function changeSong(track) {
  store.loadTrack(track);
  audio.play();
}
</script>

<template>
  <li class="track">
    <PlayTrackIcon class="track__icon" @click="changeSong(track)" />
    <p class="track__p">
      <span>{{ props.track.order }}. </span>
      <span>{{ props.track.title }}. </span>
    </p>
  </li>
</template>

<style lang="scss" scoped>
.track {
  width: 100%;
  border-bottom: 0.1rem solid map-get($map: $color, $key: "border-light");
  padding: 1rem 5%;
  @include flex(row, $justify: flex-start);

  &__icon {
    width: 2rem;
    margin-right: 2rem;
  }
  &__p {
    color: map-get($map: $color, $key: "text-dark");
    @include font(
      $family: $secondary-font-family,
      $size: map-get($map: $font-size, $key: "mv-xs")
    );
  }
}
</style>
