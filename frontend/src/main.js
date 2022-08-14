import { createApp } from "vue";
import { createPinia } from "pinia";
import { useAlbumListStore } from "@/stores/albumListStore";

import App from "./App.vue";
import router from "./router";

import "./assets/scss/app.scss";
const app = createApp(App);

app.use(createPinia());
export const store = useAlbumListStore();
app.use(router);

app.mount("#app");
