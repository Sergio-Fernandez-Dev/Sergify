<template>
  <div class="album-container">
    <img class="cover" :src="cover" />
    <div class="animation"></div>
    <div class="album-info">
      <h3 class="title-small">{{ title }}</h3>
      <h4 class="subtitle-small">{{ artist }}</h4>
    </div>
  </div>
</template>

<script setup>
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
