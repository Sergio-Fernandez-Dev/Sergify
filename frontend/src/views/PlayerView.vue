<template>
  <main class="main main--player-view">
    <PlayerComponent />
  </main>
</template>

<script setup>
import PlayerComponent from "../components/PlayerComponent.vue";
</script>

<style lang="scss" scoped>
@import "@/assets/scss/layout/main";

.main--player-view {
  @include flex(column);
  padding: 0;
}
</style>
