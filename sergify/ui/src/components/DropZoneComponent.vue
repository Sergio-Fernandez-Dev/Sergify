<script setup>
import AddAlbumIcon from "./icons/UploaderIcon.vue";
import { useUploaderStore } from "../stores/uploadStore";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUploaderStore();

const redirectToUploadForm = () => {
  router.push({ name: "upload" });
};

const uploadAlbum = (event) => {
  const files = [...event.target.files];
  const mp3Files = files.filter((file) => file.type === "audio/mpeg");
  mp3Files.forEach((file) => {
    store.uploadFile(file);
  });
  redirectToUploadForm();
};
</script>

<template>
  <div class="dropzone">
    <label for="dropzone-input">
      <AddAlbumIcon class="dropzone__icon" />
    </label>
    <input 
      id="dropzone-input"
      class="dropzone__input"
      type="file"
      accept="audio/mp3"
      multiple
      @change="uploadAlbum"
    >
  </div>
</template>

<style lang="scss" scoped>
.dropzone {
  width: 100%;
  aspect-ratio: 1 / 1;
  align-self: flex-start;
  border: 2px dashed map-get($map: $color, $key: "icon-light");
  @include flex(row);

  &__icon {
    width: 80%;
    height: 80%;
  }

  &__input {
    display: none;
  }
}
</style> 