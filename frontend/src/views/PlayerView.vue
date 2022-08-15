<template>
  <main class="main main--player-view">
    <PlayerComponent />
  </main>
</template>

<script setup>
import { getAllTracks } from "../services/apiCall";
import { onMounted } from "vue";
import { usePlaylistStore } from "../stores/playlistStore";
import { useRoute } from "vue-router";
import PlayerComponent from "../components/PlayerComponent.vue";

const albumId = useRoute().params.albumId;
const store = usePlaylistStore();

onMounted(async () => { 
  const data = await getAllTracks(albumId);
  store.addAlbumToPlaylist(data);
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/views/player";
</style>
