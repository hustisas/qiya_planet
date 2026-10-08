<!--
  * 项目信息
  *
  * Based on SmartAdmin.
  * Original Copyright (c) 2020 1024-lab, MIT License.
  * Modifications Copyright (c) 2026 qiya_planet.
-->
<template>
  <default-home-card icon="SmileOutlined" title="项目信息">
    <div class="app-qr-box">
      <div class="app-qr">
        <span class="qr-desc strong">qiya_planet</span>
        <span class="qr-desc">专注萌芽学习</span>
        <span class="qr-desc">包名：com.study.planet.wp</span>
        <span class="qr-desc">基于 SmartAdmin，MIT License</span>
      </div>
    </div>
  </default-home-card>
</template>
<script setup>
  import DefaultHomeCard from '/@/views/system/home/components/default-home-card.vue';
</script>
<style lang="less" scoped>
  .app-qr-box {
    display: flex;
    min-height: 120px;
    align-items: center;
    justify-content: space-around;
    .app-qr {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      .strong {
        font-weight: 600;
        margin-bottom: 8px;
      }
      .qr-desc {
        display: flex;
        align-items: center;
        font-size: 12px;
        text-align: center;
      }
    }
  }
</style>
