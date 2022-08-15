<template>
  <main class="main main--home-view">
    <AlbumComponent
      v-for="album in data"
      :key="album.id"
      :id="album.id"
      :title="album.title"
      :artist="album.artist.name"
      :cover="album.cover"
    />
  </main>
</template>

<script setup>
import { getAlbumColletion} from "@/services/apiCall";
import AlbumComponent from "@/components/AlbumComponent.vue";
import { onMounted, ref } from "vue";

const data = ref(null);

onMounted(async () => {
  data.value = await getAlbumColletion();
  console.log(data.value[0]);
});

</script>

<style lang="scss" scoped>
@import "@/assets/scss/views/home";
</style>
