<!--
  * 今日与节气
-->
<template>
  <a-card size="small" :bordered="false" :hoverable="true" title="今日与节气" :loading="loading">
    <a-alert
      style="margin-bottom: 16px"
      type="info"
      show-icon
      message="今日任务会出现在学生端的主按钮上。节气和皮肤只换这一天的气氛。会员开关不能打开，也不能在这里写价格。"
    />
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 4 }" style="max-width: 720px">
      <a-form-item label="今日任务" name="todayTask">
        <a-input v-model:value="form.todayTask" :maxlength="64" placeholder="例如：读一首静夜思" />
      </a-form-item>
      <a-form-item label="节气" name="festivalName">
        <a-input v-model:value="form.festivalName" :maxlength="16" placeholder="例如：寒露" />
      </a-form-item>
      <a-form-item label="皮肤" name="skinName">
        <a-input v-model:value="form.skinName" :maxlength="32" placeholder="例如：秋叶" />
      </a-form-item>
      <a-form-item label="家庭会员">
        <a-switch :checked="false" disabled />
        <span style="margin-left: 8px; color: #667085">暂未开放</span>
      </a-form-item>
      <a-form-item :wrapper-col="{ offset: 4 }">
        <a-button v-if="form.opsId" type="primary" v-privilege="'qiya:ops:update'" @click="onSubmit">保存</a-button>
        <a-button v-else type="primary" v-privilege="'qiya:ops:add'" @click="onSubmit">保存</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>
<script setup>
  import { onMounted, reactive, ref } from 'vue';
  import { message } from 'ant-design-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { qiyaApi } from '/@/api/business/qiya/qiya-api';
  import { smartSentry } from '/@/lib/smart-sentry';

  const loading = ref(false);
  const formRef = ref();
  const form = reactive({
    opsId: undefined,
    todayTask: '',
    festivalName: '',
    skinName: '',
    memberOpen: false,
  });
  const rules = {
    todayTask: [{ required: true, message: '今日任务不能为空' }],
    festivalName: [{ required: true, message: '节气不能为空' }],
    skinName: [{ required: true, message: '皮肤不能为空' }],
  };

  async function load() {
    loading.value = true;
    try {
      const res = await qiyaApi.opsQuery({ pageNum: 1, pageSize: 1, searchWord: '' });
      const row = res.data && res.data.list && res.data.list[0];
      if (row) {
        form.opsId = row.opsId;
        form.todayTask = row.todayTask;
        form.festivalName = row.festivalName;
        form.skinName = row.skinName;
      }
      form.memberOpen = false;
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      loading.value = false;
    }
  }

  function onSubmit() {
    formRef.value
      .validate()
      .then(async () => {
        SmartLoading.show();
        try {
          const payload = {
            todayTask: form.todayTask,
            festivalName: form.festivalName,
            skinName: form.skinName,
            memberOpen: false,
          };
          if (form.opsId) {
            payload.opsId = form.opsId;
            await qiyaApi.opsUpdate(payload);
          } else {
            await qiyaApi.opsAdd(payload);
          }
          message.success('保存成功');
          load();
        } catch (e) {
          smartSentry.captureError(e);
        } finally {
          SmartLoading.hide();
        }
      })
      .catch(() => {
        message.error('参数验证错误，请仔细填写表单数据!');
      });
  }

  onMounted(load);
</script>
