<template>
  <div class="album-container">
    <RouterLink :to="{ name: 'player', params: { albumId: id } }">
      <div class="animation">
        <img class="cover" :src="cover" />
        <PlayAlbumIcon class="icon" />
      </div>
    </RouterLink>
    <div class="album-info">
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
