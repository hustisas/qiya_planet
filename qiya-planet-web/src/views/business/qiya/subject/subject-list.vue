<!--
  * 科目与年级
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "科目与年级",
  "api": "subject",
  "perm": "qiya:subject",
  "idField": "subjectId",
  "nameField": "subjectName",
  "extra": "subject",
  "actionWidth": 280,
  "statusLabel": "状态",
  "statusOptions": [
    "未开放",
    "已开放"
  ],
  "searchPlaceholder": "科目 / 状态 / 年级",
  "hint": "内容没标成已齐，或者年级范围是「未定」，都不能开放。开放后只出现在学生地图，不加第五个主按钮。",
  "scrollX": 1300,
  "columns": [
    {
      "title": "科目",
      "dataIndex": "subjectName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "状态",
      "dataIndex": "statusName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "内容",
      "dataIndex": "contentReady",
      "width": 90,
      "ellipsis": false
    },
    {
      "title": "年级范围",
      "dataIndex": "gradeScope",
      "width": 160,
      "ellipsis": false
    },
    {
      "title": "学生端入口",
      "dataIndex": "entryText",
      "width": 260,
      "ellipsis": true
    }
  ],
  "fields": [
    {
      "key": "subjectName",
      "label": "科目",
      "type": "input",
      "required": true,
      "max": 32,
      "hidden": false
    },
    {
      "key": "statusName",
      "label": "状态",
      "type": "select",
      "required": false,
      "options": [
        "未开放",
        "已开放"
      ],
      "hidden": false
    },
    {
      "key": "gradeScope",
      "label": "年级范围",
      "type": "input",
      "required": false,
      "max": 32,
      "hidden": false
    },
    {
      "key": "entryText",
      "label": "学生端入口",
      "type": "input",
      "required": false,
      "max": 64,
      "hidden": false
    },
    {
      "key": "contentReady",
      "label": "内容已齐",
      "type": "switch",
      "required": false,
      "hidden": false
    }
  ]
};
</script>
