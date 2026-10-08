<!--
  * 助记审核
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "助记审核",
  "api": "review",
  "perm": "qiya:review",
  "idField": "reviewId",
  "nameField": "targetName",
  "extra": "review",
  "fixedSubject": "en",
  "actionWidth": 250,
  "drawerWidth": 680,
  "searchPlaceholder": "单词 / 助记",
  "statusLabel": "状态",
  "statusOptions": [
    "草稿",
    "通过",
    "拒绝"
  ],
  "hint": "这里只看英语助记。空句子不能通过，出现「脏、硬凑、色情、暴力」也不能通过。",
  "columns": [
    {
      "title": "对象",
      "dataIndex": "targetName",
      "width": 140,
      "ellipsis": false
    },
    {
      "title": "助记",
      "dataIndex": "content",
      "width": 360,
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
      "label": "助记",
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
