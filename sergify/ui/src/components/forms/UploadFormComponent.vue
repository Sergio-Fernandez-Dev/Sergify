<script setup>
import { useUploaderStore } from "../../stores/uploadStore";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const store = useUploaderStore();
const router = useRouter();
const form = ref({
  cover: null,
  album: null,
  artists: [],
  trackList: [],
});

const redirectToHome = () => {
  router.push({ name: "home" });
};
const getMetadata = async (file) => {
  console.log(file.name);
};

onMounted(() => {
  store.tracklist.forEach((file) => {
    console.log(file);
    getMetadata(file);
  });
});
</script>

<template>
  <form class="form" @submit.prevent="">
    <!-- <div class="form__wrapper">
      <div class="form__img" />
    </div>
    <div class form__info>
      <input
        class="form__input"
        name="artist"
        type="text"
        placeholder="Artist"
        v-model="form.data.artists[0]"
      />
      <input
        class="form__input"
        name="album"
        type="text"
        placeholder="Album"
        v-model="form.data.album"
      />
    </div>
    <div class="form__wrapper">
      <ul class="list">
        <li
          class="list__element"
          v-for="(track, index) in form.value.trackList"
          :key="index"
        >
          {{ track.position + ". " + track.title }}
        </li>
      </ul>
      <div class="form__buttons">
        <button type="submit">Guardar</button>
        <button type="button" @click="redirectToHome()">Cancelar</button>
      </div>
    </div> -->
  </form>
</template>

<style lang="scss" scoped></style>
