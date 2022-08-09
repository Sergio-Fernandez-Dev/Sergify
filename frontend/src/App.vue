<script setup>
import { RouterView } from "vue-router";
</script>

<template>
  <header>
    <img alt="Sergify logo" class="logo" src="@/assets/logo.svg"/>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}
</style>
