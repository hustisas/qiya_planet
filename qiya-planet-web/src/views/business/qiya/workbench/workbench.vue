<!--
  * 启芽工作台
-->
<template>
  <div>
    <a-alert
      style="margin-bottom: 12px"
      type="info"
      show-icon
      message="原来的企业管理、公告管理和商品管理仍留在「业务功能」里。会员收入固定显示未开通，这里不出现价格。"
    />
    <a-row :gutter="[16, 16]">
      <a-col :xs="24" :sm="12" :lg="8" v-for="item in cards" :key="item.title">
        <a-card size="small" :bordered="false" :hoverable="true">
          <a-statistic :title="item.title" :value="item.value" />
          <div class="qiya-note">{{ item.note }}</div>
        </a-card>
      </a-col>
    </a-row>
    <a-row :gutter="16" style="margin-top: 16px">
      <a-col :xs="24" :lg="14">
        <a-card size="small" title="单词掌握" :bordered="false">
          <a-table size="small" :loading="loading" :pagination="false" :dataSource="summary.masteryList || []" :columns="columns" rowKey="name" />
        </a-card>
      </a-col>
      <a-col :xs="24" :lg="10">
        <a-card size="small" title="今天" :bordered="false" :loading="loading">
          <p>今日任务：{{ summary.todayTask || '还没有' }}</p>
          <p>节气：{{ summary.festivalName || '还没有' }}</p>
          <p>最近回打开：{{ summary.reopenRate || '暂无' }}</p>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
  import { computed, onMounted, ref } from 'vue';
  import { qiyaApi } from '/@/api/business/qiya/qiya-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  const loading = ref(false);
  const summary = ref({});
  const columns = [
    { title: '掌握', dataIndex: 'name' },
    { title: '单词数', dataIndex: 'total', width: 120 },
  ];
  const cards = computed(() => [
    { title: '在学孩子', value: summary.value.studentCount || 0, note: '学生档案里还在学的家庭' },
    { title: '待审', value: summary.value.pendingReview || 0, note: '还是草稿、等着通过或拒绝' },
    { title: '重置次数', value: summary.value.resetCount || 0, note: '各场景累计重置，不是人数' },
    { title: '付费场景', value: summary.value.paySceneCount || 0, note: '只记场景，当前不收费' },
    { title: '习惯打卡', value: summary.value.habitCount || 0, note: '推广素材带回的习惯次数' },
    { title: '会员收入', value: summary.value.memberIncome || '未开通', note: '没有价格，也不能开通' },
  ]);

  async function load() {
    loading.value = true;
    try {
      const res = await qiyaApi.workbench();
      summary.value = res.data || {};
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      loading.value = false;
    }
  }

  onMounted(load);
</script>
<style scoped>
  .qiya-note {
    margin-top: 8px;
    color: #667085;
    font-size: 12px;
  }
</style>
