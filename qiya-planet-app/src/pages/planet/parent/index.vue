<template>
  <parent-studio />
</template>

<script setup>
import { onShow } from '@dcloudio/uni-app';
import ParentStudio from '@/components/planet/parent-studio.vue';
import { boot } from '@/planet/state.js';

onShow(() => {
  boot();
});
</script>