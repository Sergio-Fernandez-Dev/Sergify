<script setup>
import PlayTrackIcon from "./icons/PlayTrackIcon.vue";
defineProps({ order: Number, title: String });
</script>

<template>
  <li class="track">
    <PlayTrackIcon class="track__icon" />
    <p class="track__p">
      <span>{{ order }}. </span>
      <span>{{ title }}. </span>
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
