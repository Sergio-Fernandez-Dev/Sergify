import { defineStore } from "pinia";
import { ref } from "vue";

export const useUploaderStore = defineStore("uploader", () => {
  const tracklist = ref([]);

  function uploadFile(file) {
    tracklist.value.push(file);
  }

  return { uploadFile, tracklist };
});
