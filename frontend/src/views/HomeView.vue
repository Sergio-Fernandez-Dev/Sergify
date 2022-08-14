<template>
  <div class="main__grid">
    <AlbumComponent
      v-for="album in store.albumList"
      :key="album.id"
      :id="album.id"
      :title="album.title"
      :artist="album.artist.name"
      :cover="album.cover"
    />
  </div>
</template>

<script setup>
import { apiCall } from "@/services/apiCall";
import AlbumComponent from "@/components/AlbumComponent.vue";
import { onMounted, ref } from "vue";
import { store } from "@/main";
const data = ref(null);

if (store.albumList.length == 0) {
  console.log(store.albumList.length);
  onMounted(async () => {
    data.value = await apiCall("http://localhost:3000/albums?_expand=artist");
    data.value.forEach((album) => {
      store.addAlbumToList(album);
    });
    console.log(store.albumList);
  });
}
</script>

<style lang="scss" scoped>
@import "@/assets/scss/views/home";
</style>
