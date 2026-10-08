<!--
  * 磨耳朵
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "磨耳朵",
  "api": "album",
  "perm": "qiya:album",
  "idField": "albumId",
  "nameField": "albumName",
  "searchPlaceholder": "专辑 / 适龄",
  "hint": "磨耳朵按适龄给短音频。停用后学生端不再播放这一条。",
  "columns": [
    {
      "title": "专辑",
      "dataIndex": "albumName",
      "width": 180,
      "ellipsis": false
    },
    {
      "title": "适龄",
      "dataIndex": "levelName",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "时长",
      "dataIndex": "minutes",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "状态",
      "dataIndex": "enabledFlag",
      "width": 100,
      "ellipsis": false
    }
  ],
  "fields": [
    {
      "key": "albumName",
      "label": "专辑",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "levelName",
      "label": "适龄",
      "type": "input",
      "required": false,
      "max": 32,
      "hidden": false
    },
    {
      "key": "minutes",
      "label": "时长",
      "type": "input",
      "required": false,
      "max": 16,
      "hidden": false
    },
    {
      "key": "enabledFlag",
      "label": "启用",
      "type": "switch",
      "required": false,
      "hidden": false
    }
  ]
};
</script>
