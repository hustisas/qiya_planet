<!--
  * 推广看板
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "推广看板",
  "api": "channel",
  "perm": "qiya:channel",
  "idField": "channelId",
  "nameField": "materialName",
  "searchPlaceholder": "素材 / 状态 / 位置",
  "statusLabel": "状态",
  "statusOptions": [
    "草稿",
    "投放中",
    "已停"
  ],
  "hint": "看素材出现在哪里、有多少家庭回来。回打开和习惯次数不代表已经收费。",
  "scrollX": 1200,
  "columns": [
    {
      "title": "素材",
      "dataIndex": "materialName",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "状态",
      "dataIndex": "statusName",
      "width": 90,
      "ellipsis": false
    },
    {
      "title": "出现位置",
      "dataIndex": "showPlace",
      "width": 140,
      "ellipsis": false
    },
    {
      "title": "家庭数",
      "dataIndex": "familyCount",
      "width": 90,
      "ellipsis": false
    },
    {
      "title": "7日回打开",
      "dataIndex": "reopenRate",
      "width": 110,
      "ellipsis": false
    },
    {
      "title": "习惯次数",
      "dataIndex": "habitCount",
      "width": 100,
      "ellipsis": false
    }
  ],
  "fields": [
    {
      "key": "materialName",
      "label": "素材",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "statusName",
      "label": "状态",
      "type": "select",
      "required": false,
      "options": [
        "草稿",
        "投放中",
        "已停"
      ],
      "hidden": false
    },
    {
      "key": "showPlace",
      "label": "出现位置",
      "type": "input",
      "required": false,
      "max": 64,
      "hidden": false
    },
    {
      "key": "familyCount",
      "label": "家庭数",
      "type": "number",
      "required": false,
      "hidden": false
    },
    {
      "key": "reopenRate",
      "label": "7日回打开",
      "type": "input",
      "required": false,
      "max": 16,
      "hidden": false
    },
    {
      "key": "habitCount",
      "label": "习惯次数",
      "type": "number",
      "required": false,
      "hidden": false
    }
  ]
};
</script>
