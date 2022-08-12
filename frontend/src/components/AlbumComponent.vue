<template>
  <div class="album-container">
    <div class="album-cover">
      <img :src="cover" />
    </div>
    <div class="album-info">
      <h3>{{ title }}</h3>
      <h4>{{ artist }}</h4>
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
