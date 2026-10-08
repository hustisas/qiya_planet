<!--
  * 闯关世界
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "闯关世界",
  "api": "world",
  "perm": "qiya:world",
  "idField": "worldId",
  "nameField": "worldName",
  "searchPlaceholder": "世界 / 关卡 / 掉点词",
  "hint": "一关只练一小步。掉点词用来看孩子卡在哪里，先不把整本词书摊开。",
  "scrollX": 1300,
  "columns": [
    {
      "title": "世界",
      "dataIndex": "worldName",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "关卡",
      "dataIndex": "levelName",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "题型",
      "dataIndex": "questionMix",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "通过率",
      "dataIndex": "passRate",
      "width": 90,
      "ellipsis": false
    },
    {
      "title": "平均重试",
      "dataIndex": "retryAvg",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "掉点词",
      "dataIndex": "weakWord",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "说明",
      "dataIndex": "note",
      "width": 240,
      "ellipsis": true
    }
  ],
  "fields": [
    {
      "key": "worldName",
      "label": "世界",
      "type": "input",
      "required": true,
      "max": 32,
      "hidden": false
    },
    {
      "key": "levelName",
      "label": "关卡",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "questionMix",
      "label": "题型",
      "type": "input",
      "required": false,
      "max": 64,
      "hidden": false
    },
    {
      "key": "passRate",
      "label": "通过率",
      "type": "input",
      "required": false,
      "max": 16,
      "hidden": false
    },
    {
      "key": "retryAvg",
      "label": "平均重试",
      "type": "input",
      "required": false,
      "max": 16,
      "hidden": false
    },
    {
      "key": "weakWord",
      "label": "掉点词",
      "type": "input",
      "required": false,
      "max": 32,
      "hidden": false
    },
    {
      "key": "note",
      "label": "说明",
      "type": "textarea",
      "required": false,
      "max": 255,
      "hidden": false
    }
  ]
};
</script>
