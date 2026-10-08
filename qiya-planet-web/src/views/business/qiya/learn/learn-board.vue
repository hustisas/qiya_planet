<!--
  * 学习数据
-->
<template>
  <div>
    <a-alert
      style="margin-bottom: 12px"
      type="info"
      show-icon
      message="先看卡在哪里。英语看薄弱词，数学看还在练习的知识点。建议只针对这些点加一小步，不一次加很多题。"
    />
    <a-row :gutter="[16, 16]" style="margin-bottom: 16px">
      <a-col :xs="24" :md="8">
        <a-card size="small" :bordered="false"><a-statistic title="待审草稿" :value="summary.pendingReview || 0" /></a-card>
      </a-col>
      <a-col :xs="24" :md="8">
        <a-card size="small" :bordered="false"><a-statistic title="薄弱词" :value="weakTotal" /></a-card>
      </a-col>
      <a-col :xs="24" :md="8">
        <a-card size="small" :bordered="false"><a-statistic title="练习中的知识点" :value="practiceTotal" /></a-card>
      </a-col>
    </a-row>
    <a-row :gutter="16">
      <a-col :xs="24" :lg="12">
        <a-card size="small" title="英语薄弱词" :bordered="false">
          <a-table size="small" :loading="loading" :pagination="false" :dataSource="weakWords" :columns="wordColumns" rowKey="wordId" />
        </a-card>
      </a-col>
      <a-col :xs="24" :lg="12">
        <a-card size="small" title="数学还在练" :bordered="false">
          <a-table size="small" :loading="loading" :pagination="false" :dataSource="practiceList" :columns="knowledgeColumns" rowKey="knowledgeId" />
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>
<script setup>
  import { onMounted, ref } from 'vue';
  import { qiyaApi } from '/@/api/business/qiya/qiya-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  const loading = ref(false);
  const summary = ref({});
  const weakWords = ref([]);
  const practiceList = ref([]);
  const weakTotal = ref(0);
  const practiceTotal = ref(0);
  const wordColumns = [
    { title: '英文', dataIndex: 'en', width: 100 },
    { title: '中文', dataIndex: 'zh', width: 100 },
    { title: '年级', dataIndex: 'gradeName', width: 90 },
    { title: '例句', dataIndex: 'sentence', ellipsis: true },
  ];
  const knowledgeColumns = [
    { title: '知识点', dataIndex: 'knowledgeName' },
    { title: '年级', dataIndex: 'gradeName', width: 90 },
    { title: '前置', dataIndex: 'prevName', width: 120 },
  ];

  async function load() {
    loading.value = true;
    try {
      const [board, weak, practice] = await Promise.all([
        qiyaApi.workbench(),
        qiyaApi.wordQuery({ pageNum: 1, pageSize: 8, searchWord: '', statusName: '薄弱' }),
        qiyaApi.knowledgeQuery({ pageNum: 1, pageSize: 8, searchWord: '', statusName: '练习中' }),
      ]);
      summary.value = board.data || {};
      weakWords.value = (weak.data && weak.data.list) || [];
      practiceList.value = (practice.data && practice.data.list) || [];
      weakTotal.value = (weak.data && weak.data.total) || 0;
      practiceTotal.value = (practice.data && practice.data.total) || 0;
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      loading.value = false;
    }
  }

  onMounted(load);
</script>
