<!--
  * 诗词库
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "诗词库",
  "api": "poem",
  "perm": "qiya:poem",
  "idField": "poemId",
  "nameField": "title",
  "searchPlaceholder": "标题 / 作者 / 原文",
  "drawerWidth": 760,
  "scrollX": 1500,
  "hint": "原文按行保存。注释要能讲给孩子听，太短的注释在审核里过不了。",
  "columns": [
    {
      "title": "标题",
      "dataIndex": "title",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "作者",
      "dataIndex": "authorName",
      "width": 90,
      "ellipsis": false
    },
    {
      "title": "朝代",
      "dataIndex": "dynasty",
      "width": 70,
      "ellipsis": false
    },
    {
      "title": "体裁",
      "dataIndex": "kindName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "出处",
      "dataIndex": "sourceNote",
      "width": 140,
      "ellipsis": false
    },
    {
      "title": "原文",
      "dataIndex": "content",
      "width": 220,
      "ellipsis": true
    },
    {
      "title": "注释",
      "dataIndex": "noteText",
      "width": 200,
      "ellipsis": true
    },
    {
      "title": "译文",
      "dataIndex": "translation",
      "width": 200,
      "ellipsis": true
    }
  ],
  "fields": [
    {
      "key": "title",
      "label": "标题",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "authorName",
      "label": "作者",
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
      "key": "kindName",
      "label": "体裁",
      "type": "input",
      "required": false,
      "max": 16,
      "hidden": false
    },
    {
      "key": "sourceNote",
      "label": "出处",
      "type": "input",
      "required": false,
      "max": 64,
      "hidden": false
    },
    {
      "key": "content",
      "label": "原文",
      "type": "textarea",
      "required": true,
      "max": 500,
      "hidden": false
    },
    {
      "key": "noteText",
      "label": "注释",
      "type": "textarea",
      "required": false,
      "max": 500,
      "hidden": false
    },
    {
      "key": "translation",
      "label": "译文",
      "type": "textarea",
      "required": false,
      "max": 500,
      "hidden": false
    }
  ]
};
</script>
