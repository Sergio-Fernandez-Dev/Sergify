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
      <h3 class="title-small">{{ title }}</h3>
      <h4 class="subtitle-small">{{ artist }}</h4>
    </div>
  </div>
</template>

<script setup>
import PlayAlbumIcon from "@/components/icons/PlayAlbumIcon.vue";
import { RouterLink } from "vue-router";

defineProps({
  id: Number,
  title: String,
  artist: String,
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

  &__wrapper {
    width: 100%;
    text-align: left;
    padding: 0 0.5rem;
  }
}

</style>
