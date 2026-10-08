<!--
  * 重置与付费
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "重置与付费",
  "api": "reset",
  "perm": "qiya:reset",
  "idField": "resetId",
  "nameField": "sceneName",
  "readonly": true,
  "searchPlaceholder": "场景 / 类型 / 收费说明",
  "scrollX": 1200,
  "hint": "这里看哪些场景在重置、重置了多少次。收费一栏现在都是未开通，不写价格。",
  "columns": [
    {
      "title": "场景",
      "dataIndex": "sceneName",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "类型",
      "dataIndex": "resetKind",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "次数",
      "dataIndex": "resetCount",
      "width": 80,
      "ellipsis": false
    },
    {
      "title": "会不会收费",
      "dataIndex": "payText",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "接着做了什么",
      "dataIndex": "movedText",
      "width": 280,
      "ellipsis": true
    }
  ],
  "fields": []
};
</script>
