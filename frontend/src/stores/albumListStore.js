import { defineStore } from "pinia";
import { ref } from "vue";

export const useAlbumListStore = defineStore("albumList", () => {
  const albumList = ref([]);
  function addAlbumToList(album) {
    this.albumList.push(album);
  }

  return { albumList, addAlbumToList };
});
