<!--
  * 词库
-->
<template>
  <QiyaCrudPage :config="config" />
</template>
<script setup>
  import QiyaCrudPage from '../components/qiya-crud-page.vue';

  const config = {
  "title": "词库",
  "api": "word",
  "perm": "qiya:word",
  "idField": "wordId",
  "nameField": "en",
  "searchPlaceholder": "英文 / 中文 / 主题",
  "statusLabel": "掌握",
  "statusOptions": [
    "未学",
    "学习中",
    "已掌握",
    "薄弱"
  ],
  "hint": "按年级和主题维护单词。掌握情况会汇总到工作台，薄弱词可以回到闯关里再练。",
  "columns": [
    {
      "title": "英文",
      "dataIndex": "en",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "中文",
      "dataIndex": "zh",
      "width": 120,
      "ellipsis": false
    },
    {
      "title": "音标",
      "dataIndex": "phonetic",
      "width": 140,
      "ellipsis": false
    },
    {
      "title": "年级",
      "dataIndex": "gradeName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "主题",
      "dataIndex": "themeName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "来源",
      "dataIndex": "sourceName",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "掌握",
      "dataIndex": "mastery",
      "width": 100,
      "ellipsis": false
    },
    {
      "title": "例句",
      "dataIndex": "sentence",
      "width": 240,
      "ellipsis": true
    }
  ],
  "fields": [
    {
      "key": "en",
      "label": "英文",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "zh",
      "label": "中文",
      "type": "input",
      "required": true,
      "max": 64,
      "hidden": false
    },
    {
      "key": "phonetic",
      "label": "音标",
      "type": "input",
      "required": false,
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
      "key": "themeName",
      "label": "主题",
      "type": "input",
      "required": false,
      "max": 32,
      "hidden": false
    },
    {
      "key": "sourceName",
      "label": "来源",
      "type": "input",
      "required": false,
      "max": 32,
      "hidden": false
    },
    {
      "key": "mastery",
      "label": "掌握",
      "type": "select",
      "required": false,
      "options": [
        "未学",
        "学习中",
        "已掌握",
        "薄弱"
      ],
      "hidden": false
    },
    {
      "key": "sentence",
      "label": "例句",
      "type": "textarea",
      "required": false,
      "max": 255,
      "hidden": false
    }
  ]
};
</script>
