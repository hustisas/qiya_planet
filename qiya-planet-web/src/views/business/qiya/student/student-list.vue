<!--
  * 学生与家庭
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "学生与家庭",
  "api": "student",
  "perm": "qiya:student",
  "idField": "studentId",
  "nameField": "childName",
  "readonly": true,
  "searchPlaceholder": "孩子 / 年级 / 家长",
  "hint": "学生和家长档案在这里只查看。改年级、改关系放到后面的学生端，避免后台直接改学习记录。",
  "columns": [
    {
      "title": "孩子",
      "dataIndex": "childName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "年级",
      "dataIndex": "gradeName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "家长",
      "dataIndex": "parentName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "最近在学",
      "dataIndex": "recentText",
      "width": 280,
      "ellipsis": true
    },
    {
      "title": "状态",
      "dataIndex": "statusName",
      "width": 90,
      "ellipsis": false
    }
  ],
  "fields": []
};
</script>
