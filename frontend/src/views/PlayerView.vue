<template>
  <main class="main main--player-view">
    <PlayerComponent />
  </main>
</template>

<script setup>
import { useRoute } from "vue-router";
import PlayerComponent from "../components/PlayerComponent.vue";

const albumId = useRoute().params.albumId;

</script>

<style lang="scss" scoped>
@import "@/assets/scss/views/player";
</style>
