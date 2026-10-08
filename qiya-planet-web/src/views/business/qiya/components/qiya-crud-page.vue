<!--
  * 启芽业务列表。查询、表格、抽屉和分页沿用商品列表的做法。
-->
<template>
  <a-form class="smart-query-form">
    <a-row class="smart-query-form-row">
      <a-form-item label="关键字" class="smart-query-form-item">
        <a-input style="width: 240px" v-model:value="queryForm.searchWord" :placeholder="config.searchPlaceholder || '请输入关键字'" />
      </a-form-item>
      <a-form-item v-if="config.statusOptions" :label="config.statusLabel || '状态'" class="smart-query-form-item">
        <a-select style="width: 150px" v-model:value="queryForm.statusName" allowClear placeholder="全部">
          <a-select-option v-for="item in config.statusOptions" :key="item" :value="item">{{ item }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item v-if="config.subjectFilter && !config.fixedSubject" label="科目" class="smart-query-form-item">
        <a-select style="width: 120px" v-model:value="queryForm.subjectCode" allowClear placeholder="全部">
          <a-select-option value="en">英语</a-select-option>
          <a-select-option value="cn">语文</a-select-option>
          <a-select-option value="math">数学</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item v-if="config.fixedSubject" label="科目" class="smart-query-form-item">
        <a-input style="width: 120px" value="英语" disabled />
      </a-form-item>
      <a-form-item class="smart-query-form-item">
        <a-button-group>
          <a-button type="primary" @click="onSearch" v-privilege="config.perm + ':query'">
            <template #icon>
              <SearchOutlined />
            </template>
            查询
          </a-button>
          <a-button @click="resetQuery" v-privilege="config.perm + ':query'">
            <template #icon>
              <ReloadOutlined />
            </template>
            重置
          </a-button>
        </a-button-group>
      </a-form-item>
    </a-row>
  </a-form>

  <a-card size="small" :bordered="false" :hoverable="true">
    <a-alert v-if="config.hint" style="margin-bottom: 12px" type="info" show-icon :message="config.hint" />
    <a-row class="smart-table-btn-block">
      <div class="smart-table-operate-block">
        <a-button v-if="!config.readonly" @click="showForm()" type="primary" v-privilege="config.perm + ':add'">
          <template #icon>
            <PlusOutlined />
          </template>
          新建
        </a-button>
      </div>
    </a-row>

    <a-table
      size="small"
      :loading="tableLoading"
      :dataSource="tableData"
      :columns="tableColumns"
      :rowKey="config.idField"
      bordered
      :pagination="false"
      :scroll="{ x: config.scrollX || 1200 }"
    >
      <template #bodyCell="{ text, record, column }">
        <template v-if="column.dataIndex === 'enabledFlag'">
          <a-tag :color="text ? 'green' : 'default'">{{ text ? '启用' : '停用' }}</a-tag>
        </template>
        <template v-else-if="column.dataIndex === 'contentReady'">
          <a-tag :color="text ? 'green' : 'default'">{{ text ? '已齐' : '未齐' }}</a-tag>
        </template>
        <template v-else-if="column.dataIndex === 'statusName' || column.dataIndex === 'mastery'">
          <a-tag :color="tagColor(text)">{{ text || '-' }}</a-tag>
        </template>
        <template v-else-if="column.dataIndex === 'action'">
          <div class="smart-table-operate">
            <template v-if="config.extra === 'review'">
              <a-button @click="onDecide(record, '通过')" type="link" v-privilege="config.perm + ':decide'" :disabled="record.statusName === '通过'">通过</a-button>
              <a-button @click="onDecide(record, '拒绝')" danger type="link" v-privilege="config.perm + ':decide'" :disabled="record.statusName === '拒绝'">拒绝</a-button>
            </template>
            <a-button v-if="config.extra === 'poet'" @click="onPreview(record)" type="link" v-privilege="config.perm + ':query'">预览讲解</a-button>
            <template v-if="config.extra === 'subject'">
              <a-button @click="onReady(record)" type="link" v-privilege="config.perm + ':ready'" :disabled="record.contentReady">内容已齐</a-button>
              <a-button @click="onOpen(record)" type="link" v-privilege="config.perm + ':open'" :disabled="record.statusName === '已开放'">开放</a-button>
            </template>
            <template v-if="!config.readonly">
              <a-button @click="showForm(record)" type="link" v-privilege="config.perm + ':update'">编辑</a-button>
              <a-button @click="onDelete(record)" danger type="link" v-privilege="config.perm + ':delete'">删除</a-button>
            </template>
          </div>
        </template>
      </template>
    </a-table>

    <div class="smart-query-table-page">
      <a-pagination
        showSizeChanger
        showQuickJumper
        show-less-items
        :pageSizeOptions="PAGE_SIZE_OPTIONS"
        :defaultPageSize="queryForm.pageSize"
        v-model:current="queryForm.pageNum"
        v-model:pageSize="queryForm.pageSize"
        :total="total"
        @change="queryData"
        :show-total="(value) => `共${value}条`"
      />
    </div>

    <a-drawer
      v-if="!config.readonly"
      :title="form[config.idField] ? '编辑' : '添加'"
      :width="config.drawerWidth || 560"
      :open="visible"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
    >
      <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">
        <a-form-item v-for="field in visibleFields" :key="field.key" :label="field.label" :name="field.key">
          <a-textarea
            v-if="field.type === 'textarea'"
            v-model:value="form[field.key]"
            :rows="4"
            :maxlength="field.max"
            show-count
            :placeholder="'请输入' + field.label"
          />
          <a-input-number
            v-else-if="field.type === 'number'"
            style="width: 100%"
            v-model:value="form[field.key]"
            :min="0"
            :placeholder="'请输入' + field.label"
          />
          <a-switch v-else-if="field.type === 'switch'" v-model:checked="form[field.key]" />
          <a-select
            v-else-if="field.type === 'select'"
            v-model:value="form[field.key]"
            allowClear
            :placeholder="'请选择' + field.label"
            @change="(value) => onSelect(field, value)"
          >
            <a-select-option v-for="opt in field.options" :key="opt.value || opt" :value="opt.value || opt">
              {{ opt.label || opt }}
            </a-select-option>
          </a-select>
          <a-input v-else v-model:value="form[field.key]" :maxlength="field.max" :placeholder="'请输入' + field.label" />
        </a-form-item>
      </a-form>
      <div class="qiya-drawer-footer">
        <a-button style="margin-right: 8px" @click="onClose">取消</a-button>
        <a-button type="primary" @click="onSubmit">提交</a-button>
      </div>
    </a-drawer>
  </a-card>
</template>

<script setup>
  import { computed, nextTick, onMounted, reactive, ref } from 'vue';
  import { message, Modal } from 'ant-design-vue';
  import { SearchOutlined, ReloadOutlined, PlusOutlined } from '@ant-design/icons-vue';
  import { SmartLoading } from '/@/components/framework/smart-loading';
  import { qiyaApi } from '/@/api/business/qiya/qiya-api';
  import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
  import { smartSentry } from '/@/lib/smart-sentry';
  import _ from 'lodash';

  const props = defineProps({
    config: {
      type: Object,
      required: true,
    },
  });

  const config = props.config;
  const subjectNameMap = { en: '英语', cn: '语文', math: '数学' };

  const queryFormState = {
    searchWord: '',
    statusName: undefined,
    subjectCode: config.fixedSubject || undefined,
    pageNum: 1,
    pageSize: 10,
  };
  const queryForm = reactive(_.cloneDeep(queryFormState));
  const tableLoading = ref(false);
  const tableData = ref([]);
  const total = ref(0);
  const visible = ref(false);
  const formRef = ref();
  const form = reactive(blankForm());

  const visibleFields = computed(() => {
    return (config.fields || []).filter((field) => {
      if (field.hidden) {
        return false;
      }
      return !(config.fixedSubject && field.key === 'subjectCode');
    });
  });

  const rules = computed(() => {
    const result = {};
    visibleFields.value.forEach((field) => {
      if (field.required) {
        result[field.key] = [{ required: true, message: field.label + '不能为空' }];
      }
    });
    return result;
  });

  const tableColumns = computed(() => {
    const columns = (config.columns || []).map((item) => ({
      title: item.title,
      dataIndex: item.dataIndex,
      width: item.width,
      ellipsis: !!item.ellipsis,
    }));
    columns.push({ title: '更新时间', dataIndex: 'updateTime', width: 170 });
    if (!config.readonly || config.extra) {
      columns.push({ title: '操作', dataIndex: 'action', fixed: 'right', width: config.actionWidth || 160 });
    }
    return columns;
  });

  function blankForm() {
    const data = {};
    data[config.idField] = undefined;
    (config.fields || []).forEach((field) => {
      if (field.key === 'subjectCode' && config.fixedSubject) {
        data[field.key] = config.fixedSubject;
      } else if (field.key === 'subjectName' && config.fixedSubject) {
        data[field.key] = '英语';
      } else if (field.type === 'switch') {
        data[field.key] = field.key === 'enabledFlag';
      } else if (field.type === 'number') {
        data[field.key] = 0;
      } else if (field.key === 'statusName' && config.extra === 'review') {
        data[field.key] = '草稿';
      } else {
        data[field.key] = undefined;
      }
    });
    return data;
  }

  function tagColor(text) {
    if (text === '通过' || text === '已掌握' || text === '已开放' || text === '投放中' || text === '在学') {
      return 'green';
    }
    if (text === '拒绝' || text === '薄弱') {
      return 'red';
    }
    if (text === '草稿' || text === '学习中' || text === '练习中') {
      return 'blue';
    }
    return 'default';
  }

  function resetQuery() {
    const pageSize = queryForm.pageSize;
    Object.assign(queryForm, _.cloneDeep(queryFormState));
    queryForm.pageSize = pageSize;
    queryData();
  }

  function onSearch() {
    queryForm.pageNum = 1;
    queryData();
  }

  async function queryData() {
    tableLoading.value = true;
    try {
      const queryResult = await qiyaApi[config.api + 'Query'](queryForm);
      tableData.value = queryResult.data.list || [];
      total.value = queryResult.data.total || 0;
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      tableLoading.value = false;
    }
  }

  function showForm(row) {
    Object.assign(form, blankForm());
    if (row) {
      Object.assign(form, row);
    }
    if (config.fixedSubject) {
      form.subjectCode = config.fixedSubject;
      form.subjectName = '英语';
    }
    visible.value = true;
    nextTick(() => {
      if (formRef.value) {
        formRef.value.clearValidate();
      }
    });
  }

  function onClose() {
    Object.assign(form, blankForm());
    visible.value = false;
  }

  function onSelect(field, value) {
    if (field.key === 'subjectCode') {
      form.subjectName = subjectNameMap[value] || '';
    }
  }

  function onSubmit() {
    formRef.value
      .validate()
      .then(async () => {
        SmartLoading.show();
        try {
          const payload = {};
          (config.fields || []).forEach((field) => {
            payload[field.key] = form[field.key];
          });
          if (config.fixedSubject) {
            payload.subjectCode = config.fixedSubject;
            payload.subjectName = '英语';
          }
          if (payload.subjectCode && !payload.subjectName) {
            payload.subjectName = subjectNameMap[payload.subjectCode] || payload.subjectCode;
          }
          if (form[config.idField]) {
            payload[config.idField] = form[config.idField];
            await qiyaApi[config.api + 'Update'](payload);
            message.success('修改成功');
          } else {
            if (config.extra === 'review') {
              payload.statusName = '草稿';
            }
            await qiyaApi[config.api + 'Add'](payload);
            message.success('添加成功');
          }
          onClose();
          queryData();
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

  function onDelete(record) {
    const name = record[config.nameField] || '';
    Modal.confirm({
      title: '提示',
      content: '确定要删除【' + name + '】吗?',
      okText: '删除',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        return doDelete(record);
      },
    });
  }

  async function doDelete(record) {
    SmartLoading.show();
    try {
      await qiyaApi[config.api + 'Delete'](record[config.idField]);
      message.success('删除成功');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  function onDecide(record, decision) {
    Modal.confirm({
      title: '提示',
      content: '确定将【' + record.targetName + '】标成' + decision + '吗？',
      okText: '确定',
      cancelText: '取消',
      onOk() {
        return decide(record, decision);
      },
    });
  }

  async function decide(record, decision) {
    SmartLoading.show();
    try {
      await qiyaApi.reviewDecide({ reviewId: record.reviewId, decision: decision });
      message.success('已' + decision);
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  async function onPreview(record) {
    try {
      const res = await qiyaApi.poetPreview(record.poetId);
      Modal.info({
        title: (record.poetName || '诗人') + '的讲解',
        content: res.data || '还没有讲解',
      });
    } catch (e) {
      smartSentry.captureError(e);
    }
  }

  async function onReady(record) {
    SmartLoading.show();
    try {
      await qiyaApi.subjectReady(record.subjectId);
      message.success('已标记内容齐备');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  function onOpen(record) {
    Modal.confirm({
      title: '开放科目',
      content: '开放后只出现在学生地图，不会多出一个主按钮。确定开放【' + record.subjectName + '】吗？',
      okText: '开放',
      cancelText: '取消',
      onOk() {
        return doOpen(record);
      },
    });
  }

  async function doOpen(record) {
    SmartLoading.show();
    try {
      await qiyaApi.subjectOpen(record.subjectId);
      message.success('已开放');
      queryData();
    } catch (e) {
      smartSentry.captureError(e);
    } finally {
      SmartLoading.hide();
    }
  }

  onMounted(queryData);
</script>

<style scoped>
  .qiya-drawer-footer {
    position: absolute;
    right: 0;
    bottom: 0;
    width: 100%;
    border-top: 1px solid #e9e9e9;
    padding: 10px 16px;
    background: #fff;
    text-align: right;
    z-index: 1;
  }
</style>
