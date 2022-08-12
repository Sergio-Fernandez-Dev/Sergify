<template>
  <main>
    <div class="home-grid">
      <AlbumComponent
        v-for="album in data"
        :key="album.id"
        :id="album.id"
        :title="album.title"
        :artist="album.artist.name"
        :cover="album.cover"
      />
    </div>
  </main>
</template>

<script setup>
import { apiCall } from "../services/apiCall";
import AlbumComponent from "../components/AlbumComponent.vue";
import { onMounted, ref } from "vue";

const data = ref(null);

onMounted(async () => {
  data.value = await apiCall("http://localhost:3000/albums?_expand=artist");
  console.log(data);
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/views/home";
</style>
