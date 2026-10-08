<!--
  * 审核中心
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "审核中心",
  "api": "review",
  "perm": "qiya:review",
  "idField": "reviewId",
  "nameField": "targetName",
  "extra": "review",
  "subjectFilter": true,
  "actionWidth": 250,
  "drawerWidth": 680,
  "scrollX": 1300,
  "searchPlaceholder": "对象 / 内容",
  "statusLabel": "状态",
  "statusOptions": [
    "草稿",
    "通过",
    "拒绝"
  ],
  "hint": "英语不能出现「脏、硬凑、色情、暴力」。语文注释少于 4 个字不能通过。数学要通过，必须写出「因为」或「原因」。状态只能在这里通过或拒绝。",
  "columns": [
    {
      "title": "科目",
      "dataIndex": "subjectName",
      "width": 80,
      "ellipsis": false
    },
    {
      "title": "对象",
      "dataIndex": "targetName",
      "width": 140,
      "ellipsis": false
    },
    {
      "title": "内容",
      "dataIndex": "content",
      "width": 320,
      "ellipsis": true
    },
    {
      "title": "状态",
      "dataIndex": "statusName",
      "width": 90,
      "ellipsis": false
    }
  ],
  "fields": [
    {
      "key": "subjectCode",
      "label": "科目",
      "type": "select",
      "required": true,
      "options": [
        {
          "value": "en",
          "label": "英语"
        },
        {
          "value": "cn",
          "label": "语文"
        },
        {
          "value": "math",
          "label": "数学"
        }
      ],
      "hidden": false
    },
    {
      "key": "subjectName",
      "label": "科目名",
      "type": "input",
      "required": true,
      "max": 16,
      "options": null,
      "hidden": true
    },
    {
      "key": "targetName",
      "label": "对象",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "content",
      "label": "内容",
      "type": "textarea",
      "required": true,
      "max": 500,
      "hidden": false
    },
    {
      "key": "statusName",
      "label": "状态",
      "type": "input",
      "required": true,
      "max": 16,
      "options": null,
      "hidden": true
    }
  ]
};
</script>
