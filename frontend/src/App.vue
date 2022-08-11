<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <header>
    <img alt="Sergify logo" class="header__img" src="@/assets/logo.svg" />
  </header>
  <RouterView />
  <footer>
    <p>&copy; Sergio Fernández Fernández</p>
  </footer>
</template>

<style lang="scss" src="@/assets/scss/app.scss"></style>
