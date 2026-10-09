<template>
  <planet-studio tab="home" />
</template>

<script setup>
import { onShow } from '@dcloudio/uni-app';
import PlanetStudio from '@/components/planet/planet-studio.vue';
import { boot, ensureTab } from '@/planet/state.js';

onShow(() => {
  boot();
  ensureTab('home');
});
</script>