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
@import "@/assets/scss/components/album";
</style>
