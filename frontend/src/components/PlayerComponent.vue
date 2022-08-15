<template>
  <section class="player">
    <div class="player__wrapper">
      <img src="" alt="artist" class="player__img" />
      <div class="player__info">
        <h3 class="title-big"></h3>
        <h4 class="subtitle-big"></h4>
      </div>
    </div>
    <div class="media">
      <div class="media__wrapper">
        <div class="media__icon"></div>
        <div class="media__icon"></div>
        <div class="media__icon"></div>
        <div class="media__icon"></div>
      </div>
      <div class="media__wrapper">
        <p class="media__time-info">0:30 / 2:20</p>
        <div class="media__timebar"></div>
      </div>
    </div>
    <div class="playlist">
      <ul class="playlist__ul">
        <li class="playlist__li">
          <!-- <TrackComponent 
            v-for="track in tracks"
            :key="track.id"
            :order="track.order"
            :title="track.title"
            :url="track.url" 
          />-->
        </li>
      </ul>
    </div>
  </section>
</template>

<script setup>
import TrackComponent from './TrackComponent.vue';</script>

<style lang="scss" scoped>
@import "@/assets/scss/components/player";
</style>
