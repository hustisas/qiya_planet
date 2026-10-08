<!--
  * 知识点
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "知识点",
  "api": "knowledge",
  "perm": "qiya:knowledge",
  "idField": "knowledgeId",
  "nameField": "knowledgeName",
  "searchPlaceholder": "知识点 / 年级",
  "statusLabel": "状态",
  "statusOptions": [
    "未练",
    "练习中",
    "已掌握"
  ],
  "hint": "先跟小学知识点。前置没会，就不要把后面的题推到前面。",
  "columns": [
    {
      "title": "知识点",
      "dataIndex": "knowledgeName",
      "width": 180,
      "ellipsis": false
    },
    {
      "title": "年级",
      "dataIndex": "gradeName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "练法",
      "dataIndex": "practiceType",
      "width": 140,
      "ellipsis": false
    },
    {
      "title": "前置",
      "dataIndex": "prevName",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "状态",
      "dataIndex": "statusName",
      "width": 100,
      "ellipsis": false
    }
  ],
  "fields": [
    {
      "key": "knowledgeName",
      "label": "知识点",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "gradeName",
      "label": "年级",
      "type": "select",
      "required": false,
      "options": [
        "一年级",
        "二年级",
        "三年级",
        "四年级",
        "五年级",
        "六年级"
      ],
      "hidden": false
    },
    {
      "key": "practiceType",
      "label": "练法",
      "type": "input",
      "required": false,
      "max": 64,
      "hidden": false
    },
    {
      "key": "prevName",
      "label": "前置",
      "type": "input",
      "required": false,
      "max": 64,
      "hidden": false
    },
    {
      "key": "statusName",
      "label": "状态",
      "type": "select",
      "required": false,
      "options": [
        "未练",
        "练习中",
        "已掌握"
      ],
      "hidden": false
    }
  ]
};
</script>
