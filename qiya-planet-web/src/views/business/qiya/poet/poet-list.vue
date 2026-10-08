<!--
  * 诗人馆
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "诗人馆",
  "api": "poet",
  "perm": "qiya:poet",
  "idField": "poetId",
  "nameField": "poetName",
  "extra": "poet",
  "actionWidth": 220,
  "drawerWidth": 680,
  "scrollX": 1200,
  "searchPlaceholder": "诗人 / 朝代",
  "hint": "讲解是给学生听的话。预览只看文字，不会在后台播放声音。",
  "columns": [
    {
      "title": "诗人",
      "dataIndex": "poetName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "朝代",
      "dataIndex": "dynasty",
      "width": 80,
      "ellipsis": false
    },
    {
      "title": "简介",
      "dataIndex": "intro",
      "width": 280,
      "ellipsis": true
    },
    {
      "title": "讲解",
      "dataIndex": "scriptText",
      "width": 280,
      "ellipsis": true
    }
  ],
  "fields": [
    {
      "key": "poetName",
      "label": "诗人",
      "type": "input",
      "required": true,
      "max": 32,
      "hidden": false
    },
    {
      "key": "dynasty",
      "label": "朝代",
      "type": "input",
      "required": false,
      "max": 16,
      "hidden": false
    },
    {
      "key": "intro",
      "label": "简介",
      "type": "textarea",
      "required": false,
      "max": 500,
      "hidden": false
    },
    {
      "key": "scriptText",
      "label": "讲解",
      "type": "textarea",
      "required": false,
      "max": 500,
      "hidden": false
    }
  ]
};
</script>
