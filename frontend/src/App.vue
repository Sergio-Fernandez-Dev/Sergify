<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <header class="header">
    <img class="header__img" alt="Sergify logo" src="@/assets/logo.svg" />
  </header>
  <main class="main">
    <RouterView />
  </main>
  <footer class="footer">
    <span class="footer__span"></span>
    <p class="footer__p">&copy; Sergio Fernández Fernández</p>
  </footer>
</template>

<style lang="scss" src="@/assets/scss/app.scss"></style>
