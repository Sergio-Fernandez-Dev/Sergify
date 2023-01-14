<script setup>
import { usePlaylistStore } from "../stores/playlistStore";
import MusicalNoteIcon from "./icons/MusicalNoteIcon.vue";
import PlayTrackIcon from "./icons/PlayTrackIcon.vue";

defineProps({ track: Object });
const store = usePlaylistStore();
const checkActiveTrack = (track) => {
  return store.currentTrack.id === track.id;
};
</script>

<template>
  <li class="track" :class="{ 'track--active': checkActiveTrack(track) }">
    <PlayTrackIcon
      class="track__icon"
      @click="store.loadTrack(track)"
      v-if="!checkActiveTrack(track)"
    />
    <MusicalNoteIcon class="track__icon" v-else />
    <p class="track__p">
      <span>{{ track.position }}. </span>
      <span>{{ track.title }}. </span>
    </p>
  </li>
</template>

<style lang="scss" scoped>
.track {
  width: 100%;
  border-bottom: 0.1rem solid map-get($map: $color, $key: "border-light");
  padding: 1rem 5%;
  @include flex(row, $justify: flex-start);

  &--active {
    background-color: map-get($map: $color, $key: "contrast");
  }

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
