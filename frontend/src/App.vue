<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <header>
    <img alt="Sergify logo" class="logo" src="@/assets/logo.svg" />
  </header>

  <RouterView />
</template>

<style lang="scss" src="./scss/app.scss"></style>
