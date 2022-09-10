<template>
  <div class="track-wrapper">
    <p>
      <span>{{ order }}. </span>
      <span>{{ title }}. </span>
    </p>
  </div>
</template>

<script setup>
defineProps({ order: Number, title: String });
</script>

<style lang="scss" scoped></style>
