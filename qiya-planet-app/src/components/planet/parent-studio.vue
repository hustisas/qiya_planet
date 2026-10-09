<template>
  <view class="qp-app qp-parent" :class="'qp-grade-' + planet.gradeKey">
    <scroll-view scroll-y class="qp-scroll">
      <view v-if="play.parentView === 'phome'" class="qp-page">
        <view class="qp-title">豆豆这周</view>
        <view class="qp-sub">{{ info.parentGrade }}</view>
        <view class="qp-card">{{ info.parentCard }}</view>
        <view v-if="planet.lastSpeech" class="qp-card">最近跟读 {{ planet.lastSpeech.title }}：{{ planet.lastSpeech.label }}。{{ planet.lastSpeech.hint }}</view>
        <button class="qp-ghost" @click="play.parentView = 'pfollow'">查看建议与跟进点</button>
        <button class="qp-ghost" @click="play.parentView = 'pmember'">家庭会员 · 暂未开放</button>
        <view class="qp-grid qp-kpis">
          <view class="qp-card"><b>86 分钟</b><text class="qp-tiny">学习时长</text></view>
          <view class="qp-card"><b>{{ planet.streak }} 天</b><text class="qp-tiny">连续探险</text></view>
          <view class="qp-card"><b>18</b><text class="qp-tiny">新词</text></view>
          <view class="qp-card"><b>68%</b><text class="qp-tiny">退位减法</text></view>
        </view>
        <button class="qp-cta" @click="play.parentView = 'pfollow'">调整数学跟进点</button>
        <button class="qp-ghost" @click="play.parentView = 'pphoto'">拍照记录</button>
      </view>

      <view v-else-if="play.parentView === 'pwords'" class="qp-page">
        <view class="qp-title">单词与闯关</view>
        <view class="qp-item"><b>apple / cup / plate</b><text class="qp-tiny">已较熟</text></view>
        <view class="qp-item"><b>fork</b><text class="qp-tiny">困难词 · 明天复习</text></view>
        <view v-for="item in planet.speechReviews" :key="item.id" class="qp-item">
          <b>{{ item.title }}</b><text class="qp-tiny">读音不准确 · 孩子自己放进复习</text>
        </view>
        <view class="qp-item"><b>好吃的</b><text class="qp-tiny">第 3 / 6 关 · 2 星</text></view>
        <button class="qp-ghost" @click="toast('已整理今日新词：apple、cup、plate。这是演示，不会下载文件。')">导出今日新词</button>
      </view>

      <view v-else-if="play.parentView === 'pfollow'" class="qp-page">
        <view class="qp-title">数学跟进</view>
        <view class="qp-sub">跟小学知识点，不绑教材章节</view>
        <button class="qp-item" :class="{ 'is-pick': pointOn('main') }" @click="setMathPoint('main', '当前跟进仍是退位减法。', false)">
          <b>退位减法</b><text class="qp-tiny">当前跟进</text>
        </button>
        <button class="qp-item" :class="{ 'is-pick': pointOn('fallback') }" @click="setMathPoint('fallback', '已建议回退到 20 以内。学生端不会被锁住。', false)">
          <b>20 以内退位</b><text class="qp-tiny">建议回退</text>
        </button>
        <button class="qp-item" @click="setMathPoint('later', '表内乘法先不练。不会因此跳到后面的知识点。', false)">
          <b>表内乘法</b><text class="qp-tiny">已掌握，可先不练</text>
        </button>
        <view class="qp-hint">挑战档仍停在小学该点，不会跳到初中。</view>
        <view class="qp-sub">{{ previewTitle() }}</view>
        <button v-for="item in mathPreview()" :key="item.label" class="qp-item" @click="toast(item.act)">
          <b>{{ item.label }}</b><text class="qp-tiny">{{ item.kind }} · 口算</text>
        </button>
        <button class="qp-ghost" @click="toast('已整理今日 10 题打印草稿。这是演示，不会下载文件，页面上也不标价。')">打印今日 10 题</button>
        <view class="qp-sub">孩子端没有价格，这组也不锁。</view>
      </view>

      <view v-else-if="play.parentView === 'pset'" class="qp-page">
        <view class="qp-title">计划与隐私</view>
        <button class="qp-item" :class="{ 'is-pick': planet.settings.limit }" @click="toggleSetting('limit')">每日时长上限 25 分钟</button>
        <button class="qp-item" :class="{ 'is-pick': planet.settings.mnemonic }" @click="toggleSetting('mnemonic')">助记文字梗：{{ planet.settings.mnemonic ? '开' : '关' }}</button>
        <button class="qp-item" :class="{ 'is-pick': planet.settings.photo }" @click="toggleSetting('photo')">允许拍照识词：{{ planet.settings.photo ? '开' : '关' }}</button>
        <button class="qp-item" :class="{ 'is-pick': planet.settings.speak }" @click="toggleSetting('speak')">允许跟读纠音：{{ planet.settings.speak ? '开' : '关' }}</button>
        <button class="qp-item" :class="{ 'is-pick': planet.settings.night }" @click="toggleSetting('night')">晚 21:30 后只开磨耳朵</button>
        <button class="qp-item" :class="{ 'is-pick': planet.settings.bottle }" @click="toggleSetting('bottle')">跨岛漂流瓶：开，每天最多 1 个</button>
        <button class="qp-item" @click="cycleGrade">{{ info.parentSet }}</button>
        <view class="qp-hint">改年级只在家长端。已会的关和单词本都保留。孩子端的年级不能点。</view>
        <button class="qp-ghost" @click="play.parentView = 'pphoto'">查看拍照记录</button>
      </view>

      <view v-else-if="play.parentView === 'pphoto'" class="qp-page">
        <view class="qp-title">拍照记录</view>
        <view class="qp-sub">只看认过的词。原图不长期保存，也不认人脸。</view>
        <view class="qp-item"><b>{{ saved.en }} {{ saved.zh }}</b><text class="qp-tiny">今天 · 已入关</text></view>
        <view class="qp-item"><b>lamp 台灯</b><text class="qp-tiny">昨天 · 已收藏</text></view>
      </view>

      <view v-else class="qp-page">
        <view class="qp-title">家庭会员</view>
        <view class="qp-sub">先看免费能学什么，再看今天还剩几次</view>
        <view class="qp-card">一直免费：课标诗词、小学知识点、基础英语世界（我家 / 好吃的 / 小动物）、闯关、磨耳朵。</view>
        <view class="qp-item"><b>{{ photoUsedText() }}</b><text class="qp-tiny">今天还可拍 {{ planet.photoLeft }} 次</text></view>
        <view class="qp-item"><b>{{ speakUsedText() }}</b><text class="qp-tiny">{{ planet.speakLeft > 0 ? '今天还可以跟读口评' : '今天跟读口评已用完，听和闯关不锁' }}</text></view>
        <view class="qp-card">收费前仍可看 3 条建议。收费后免费保留 1 条建议和 1 个薄弱点，图谱不锁。</view>
        <button class="qp-cta" @click="memberClosed">暂未开放</button>
        <view class="qp-sub">月卡、年卡以后是道具直购。不展示代币，不自动续费。孩子端不显示价格。</view>
      </view>
    </scroll-view>
    <view class="qp-parent-nav">
      <button :class="{ 'is-on': play.parentView === 'phome' }" @click="play.parentView = 'phome'">概览</button>
      <button :class="{ 'is-on': play.parentView === 'pwords' }" @click="play.parentView = 'pwords'">单词</button>
      <button :class="{ 'is-on': play.parentView === 'pfollow' }" @click="play.parentView = 'pfollow'">数学</button>
      <button :class="{ 'is-on': play.parentView === 'pmember' }" @click="play.parentView = 'pmember'">会员</button>
      <button :class="{ 'is-on': play.parentView === 'pset' || play.parentView === 'pphoto' }" @click="play.parentView = 'pset'">设置</button>
    </view>
    <view v-if="play.toastOn" class="qp-toast">{{ play.toast }}</view>
  </view>
</template>

<script setup>
import { computed } from "vue";
import {
  cycleGrade,
  gradeInfo,
  mathPreview,
  memberClosed,
  photoUsedText,
  planet,
  play,
  pointOn,
  previewTitle,
  savedPhotoWord,
  setMathPoint,
  speakUsedText,
  toast,
  toggleSetting,
} from "@/planet/state.js";

const info = computed(() => gradeInfo());
const saved = computed(() => savedPhotoWord());
</script>