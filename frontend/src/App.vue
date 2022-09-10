<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <header class="header">
    <img class="header__img" alt="Sergify logo" src="@/assets/logo.svg" />
  </header>
  <RouterView />
  <footer class="footer">
    <span class="footer__span"></span>
    <p class="footer__p">&copy; Sergio Fernández Fernández</p>
  </footer>
</template>

<style lang="scss">
@import "@/assets/scss/base/reset";
@import "@/assets/scss/base/typo";

html {
  font-size: 62.5%;
}
body {
  min-height: 100vh;
  background-color: white;
  font-size: 16rem;
  font-family: $primary-font-family;
  color: map-get($color, "text-light");
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  #app {
    min-height: 100vh;
    min-width: 100vw;
    background-image: url(@/assets/img/background.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;

    @include flex(column, $justify: flex-start);
  }
}

.header {
  position: relative;
  width: 100vw;
  height: 5.5rem;
  background-color: map-get($color, "primary");
  padding: 1.2rem;
  @include flex(row, $align: baseline);

  &__img {
    position: absolute;
    width: 19.5rem;
    z-index: 2;
  }

  @include screen($screen-md) {
    height: 11rem;
    padding: 1.7rem;
    &__img {
      width: 42.5rem;
    }
  }
}
.footer {
  display: none;

  @include screen($screen-md) {
    @include flex(column);
    position: absolute;
    bottom: 0;
    width: 100%;
    max-width: 120rem;
    height: 10rem;
    padding: 2rem auto;
  }

  &__span {
    width: 85%;
    height: 0.1rem;
    background-color: map-get($color, "text-light");
    margin-bottom: 2rem;
  }

  &__p {
    font-size: map-get($font-size, "mv-sm");
  }
}
</style>
