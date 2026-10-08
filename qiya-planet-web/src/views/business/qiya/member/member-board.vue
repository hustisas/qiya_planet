<!--
  * 家庭会员
-->
<template>
  <div>
    <a-card size="small" :bordered="false" :loading="loading">
      <a-result status="info" title="家庭会员未开通" :sub-title="messageText" />
      <a-alert type="warning" show-icon message="这个页面不展示价格，也没有开通按钮。下面只列出可能以后收费的重置场景，方便看用户卡在哪里。" />
    </a-card>
    <a-card size="small" title="可能收费的场景" :bordered="false" style="margin-top: 16px">
      <a-table size="small" :loading="loading" :pagination="false" :dataSource="scenes" :columns="columns" rowKey="resetId" />
    </a-card>
  </div>
</template>
<script setup>
  import { onMounted, ref } from 'vue';
  import { qiyaApi } from '/@/api/business/qiya/qiya-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  const loading = ref(false);
  const messageText = ref('暂未开放。学生端不出现价格，也不提供开通按钮。');
  const scenes = ref([]);
  const columns = [
    { title: '场景', dataIndex: 'sceneName' },
    { title: '类型', dataIndex: 'resetKind', width: 120 },
    { title: '次数', dataIndex: 'resetCount', width: 80 },
    { title: '会不会收费', dataIndex: 'payText', width: 140 },
    { title: '接着做了什么', dataIndex: 'movedText', ellipsis: true },
  ];

  async function load() {
    loading.value = true;
    try {
      const [member, resets] = await Promise.all([
        qiyaApi.memberSummary(),
        qiyaApi.resetQuery({ pageNum: 1, pageSize: 20, searchWord: '付费场景' }),
      ]);
      if (member.data && member.data.message) {
        messageText.value = member.data.message;
      }
      scenes.value = (resets.data && resets.data.list) || [];
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      loading.value = false;
    }
  }

  onMounted(load);
</script>
