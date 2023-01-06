<template>
  <div class="album">
    <RouterLink
      class="album__link"
      :to="{ name: 'player', params: { albumId: id } }"
    >
      <img class="album__img" :src="cover" />
      <PlayAlbumIcon class="album__icon" />
    </RouterLink>
    <div class="album__wrapper">
      <h3 class="album__title">{{ title }}</h3>
      <h4 class="album__subtitle" v-for="artist in artists" :key="artist.id">
        {{ artist.name }}
      </h4>
    </div>
  </div>
</template>

<script setup>
import PlayAlbumIcon from "@/components/icons/PlayAlbumIcon.vue";
import { RouterLink } from "vue-router";

defineProps({
  id: Number,
  title: String,
  artists: Array,
  cover: String,
});
</script>

<style lang="scss" scoped>
.album {
  width: 100%;
  height: auto;
  @include flex(column);

  &__link {
    position: relative;
  }

  &__img {
    width: 100%;
    height: auto;
  }

  &__icon {
    fill: white;
    background-color: map-get($map: $color, $key: "icon-dark");
    z-index: 3;
    position: absolute;
    left: 0;
    width: 100%;
    opacity: 0%;

    &:hover {
      opacity: 30%;
    }

    &:active {
      opacity: 50%;
    }
  }

  &__title {
    @include font(
      $family: $primary-font-family,
      $size: map-get($map: $font-size, $key: "mv-sm")
    );

    @include screen($screen-md) {
      font-size: map-get($font-size, "mv-lg");
    }
  }

  &__subtitle {
    @include font(
      $family: $primary-font-family,
      $size: map-get($map: $font-size, $key: "mv-xs"),
      $style: italic,
      $weight: 300
    );

    @include screen($screen-md) {
      font-size: map-get($font-size, "mv-md");
    }
  }

  &__wrapper {
    width: 100%;
    text-align: left;
    padding: 0 0.5rem;
  }
}
</style>
