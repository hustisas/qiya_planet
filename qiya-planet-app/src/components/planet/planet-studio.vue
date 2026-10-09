<template>
  <view class="qp-app" :class="'qp-grade-' + planet.gradeKey">
    <scroll-view scroll-y class="qp-scroll">
      <view v-if="view === 'home'" class="qp-page">
        <view class="qp-row">
          <view>
            <view class="qp-title">{{ info.greet }}</view>
            <view class="qp-sub"><text class="qp-chip">{{ info.grade }}</text> {{ info.sub }}</view>
          </view>
          <button class="qp-avatar" @click="openView('mine')">🌱</button>
        </view>
        <view class="qp-row qp-planet-row">
          <view class="qp-planet">🌱</view>
          <text class="qp-chip">连续探险 {{ planet.streak }} 天</text>
        </view>
        <view class="qp-grid">
          <button class="qp-card" @click="openView('listen')"><text class="qp-tiny">听一听</text><b>{{ info.listen }}</b></button>
          <button class="qp-card" @click="openView(planet.gradeKey === 'pre' ? 'listen' : 'map')"><text class="qp-tiny">闯一闯</text><b>{{ info.play }}</b></button>
          <button class="qp-card" @click="openPoem('jing')"><text class="qp-tiny">读一读</text><b>{{ info.read }}</b></button>
          <button class="qp-card" @click="openView('math')"><text class="qp-tiny">练一练</text><b>{{ info.math }}</b></button>
        </view>
        <button class="qp-cta" @click="continueToday">{{ info.cta }}</button>
        <view class="qp-sub">{{ info.note }}</view>
        <view class="qp-hint">{{ info.hint }}</view>
        <view v-if="info.today" class="qp-card qp-only-upper">{{ info.today }}</view>
        <view v-if="info.mastery" class="qp-card qp-only-mid">{{ info.mastery }}</view>
      </view>

      <view v-else-if="view === 'map'" class="qp-page">
        <view class="qp-row">
          <view>
            <view class="qp-title">听力海湾</view>
            <view class="qp-sub">点亮主题世界，把词收进单词本</view>
          </view>
          <button class="qp-mini" @click="openView('book')">本</button>
        </view>
        <view class="qp-grid">
          <button
            v-for="world in worldList"
            :key="world.id"
            class="qp-card"
            :class="{ 'qp-current': world.state === 'current', 'qp-ok': world.state === 'done', 'qp-lock': world.state === 'lock' }"
            @click="onWorld(world)"
          >
            {{ world.emoji }} <b>{{ world.name }}</b>
            <text class="qp-tiny">{{ world.note }}</text>
          </button>
          <button class="qp-cta" @click="openView('photocam')">拍照收词</button>
        </view>
        <button class="qp-cta" @click="startBattle(false)">开始闯关</button>
        <button class="qp-ghost" @click="startSpell()">拼一拼</button>
        <button class="qp-ghost" @click="startHearSpell()">听音写</button>
      </view>

      <view v-else-if="view === 'battle'" class="qp-page">
        <view class="qp-row">
          <text>修理袋 {{ bagCount() }}</text>
          <text>{{ play.battleIndex + 1 }} / 4</text>
          <text>★ 2</text>
        </view>
        <view class="qp-lead">{{ question.lead || info.battle }}</view>
        <view class="qp-question">{{ question.q }}</view>
        <button class="qp-ghost" @click="playSound">🔊 {{ question.focus }}</button>
        <view class="qp-choices">
          <button
            v-for="(choice, index) in question.choices"
            :key="choice.text"
            class="qp-choice"
            :class="choiceClass(index)"
            @click="answerBattle(index)"
          >{{ choice.text }}</button>
        </view>
        <view class="qp-hint qp-hide-pre">修理袋：fork 本关结束前还会再出现一次</view>
        <view v-if="play.battleHintOn" class="qp-hint">{{ play.battleHint }}</view>
      </view>

      <view v-else-if="view === 'settle'" class="qp-page">
        <view class="qp-title">灯塔亮了{{ starWords }}</view>
        <view class="qp-question">{{ starLine(play.stars) }}</view>
        <view class="qp-card">
          <view class="qp-sub">新会的词</view>
          <view class="qp-pills"><text v-for="word in gotWords()" :key="word" class="qp-pill">{{ word }}</text></view>
          <view class="qp-sub">还要再修</view>
          <view class="qp-pills"><text v-if="missedWords().length === 0" class="qp-pill">今天没有要修的词</text><text v-for="word in missedWords()" :key="word" class="qp-pill is-warn">{{ word }}</text></view>
        </view>
        <view class="qp-sub">明天会遇见还没稳的词。先回星球也算今天完成。</view>
        <view v-if="info.settle" class="qp-sub qp-only-upper">{{ info.settle }}</view>
        <button class="qp-cta" @click="openView('home')">回星球</button>
        <button class="qp-ghost" @click="startBattle(true)">还想再闯一关</button>
      </view>

      <view v-else-if="view === 'book'" class="qp-page">
        <view class="qp-title">我的单词本</view>
        <view class="qp-pills">
          <button class="qp-chip-btn" :class="{ 'is-on': planet.bookTab === 'learning' }" @click="setBookTab('learning')">闯关中</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.bookTab === 'photo' }" @click="setBookTab('photo')">拍照词</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.bookTab === 'hard' }" @click="setBookTab('hard')">困难词</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.bookTab === 'done' }" @click="setBookTab('done')">已掌握</button>
        </view>
        <view v-if="planet.bookTab === 'hard' && planet.speechReviews.length" class="qp-hint">读得不准的，可以自己放进这里。下次从这里再读。</view>
        <view v-for="word in bookWords()" :key="word.tab + word.en + (word.reviewId || word.note)" class="qp-card">
          <b>{{ word.en }}</b>
          <text class="qp-tiny">{{ word.note }}</text>
          <button v-if="word.reviewId" class="qp-ghost" @click="againReview(word.reviewId)">再读一次</button>
          <button v-if="word.reviewId" class="qp-ghost" @click="dropReview(word.reviewId)">移出复习</button>
          <button v-if="canSpell(word.en)" class="qp-ghost" @click="startSpell(word.en)">拼一拼</button>
          <button v-if="canSpell(word.en)" class="qp-ghost" @click="startHearSpell(word.en)">听音写</button>
        </view>
        <button class="qp-cta" @click="startBattle(false)">复习这些词</button>
        <button class="qp-ghost" @click="startSpell()">拼这些词</button>
        <button class="qp-ghost" @click="startHearSpell()">听音写</button>
      </view>

      <view v-else-if="view === 'photocam'" class="qp-page">
        <view class="qp-row">
          <button class="qp-ghost" @click="openView('map')">返回</button>
          <text class="qp-chip">还可拍 {{ planet.photoLeft }} 次</text>
        </view>
        <view class="qp-finder"><b>☕</b><p>拍一件东西</p></view>
        <view class="qp-hint">放在框中间。只拍一件，不要拍人脸。</view>
        <view class="qp-choices">
          <button class="qp-choice" @click="shootPhoto(true)">相册</button>
          <button class="qp-choice qp-key-ok" @click="shootPhoto(false)">拍</button>
          <button class="qp-choice" @click="toggleFlash">{{ planet.flashOn ? '手电开' : '手电' }}</button>
        </view>
        <button class="qp-ghost" @click="blurPhoto">演示：拍糊了</button>
      </view>

      <view v-else-if="view === 'photolook'" class="qp-page">
        <view class="qp-finder qp-looking"><b>☕</b><p>芽芽在看这是什么</p></view>
        <view class="qp-sub">看完会自己出现候选。也可以取消，取消不算次数。</view>
        <button class="qp-cta" @click="readyPhoto">芽芽看完了</button>
        <button class="qp-ghost" @click="cancelPhoto">取消</button>
      </view>

      <view v-else-if="view === 'photopick'" class="qp-page">
        <view class="qp-sub">今天 {{ 3 - planet.photoLeft }}/3 · 点一个你认得的，不用重拍</view>
        <view class="qp-cam-shot">{{ photo.emoji }}</view>
        <view class="qp-question">{{ photo.en }}</view>
        <view class="qp-hint">{{ photo.ipa }}  {{ photo.zh }}<br />{{ photo.line }}</view>
        <view class="qp-pills">
          <button
            v-for="card in photoCards()"
            :key="card.en"
            class="qp-chip-btn"
            :class="{ 'is-on': planet.photoPick === card.en }"
            @click="pickCand(card.en)"
          >{{ card.zh }} {{ card.en }}</button>
        </view>
        <button class="qp-cta" @click="keepPhoto">就是这个</button>
        <button class="qp-ghost" @click="openView('photocam')">都不是，再拍一张</button>
      </view>

      <view v-else-if="view === 'photoblur'" class="qp-page">
        <view class="qp-title">再拍清楚一点</view>
        <view class="qp-sub">芽芽没看清。这次不占拍照次数。</view>
        <view class="qp-card">离近一点，只拍一件东西。不要拍人脸。</view>
        <button class="qp-cta" @click="openView('photocam')">再拍一张</button>
        <button class="qp-ghost" @click="openView('battle')">去闯关</button>
      </view>

      <view v-else-if="view === 'photo'" class="qp-page">
        <view class="qp-sub">已确认 · 放进好吃的，不新开世界</view>
        <view class="qp-cam-shot">{{ saved.emoji }}</view>
        <view class="qp-question">{{ saved.en }}</view>
        <view class="qp-hint">{{ saved.ipa }}  {{ saved.zh }}<br />{{ saved.line }}</view>
        <view class="qp-row">
          <button class="qp-ghost" @click="playStandard">听一听</button>
          <button class="qp-ghost" @click="followRead">跟我读</button>
          <button class="qp-ghost" @click="startSpell(saved.en)">拼这个词</button>
          <button class="qp-ghost" @click="startHearSpell(saved.en)">听音写</button>
        </view>
        <button class="qp-cta" @click="openView('battle')">用它闯一关</button>
        <button class="qp-ghost" @click="openView('book')">放入单词本</button>
        <button class="qp-ghost" @click="openView('photocam')">再拍一张</button>
        <button class="qp-ghost" @click="openView('photolimit')">演示：今天拍照用完</button>
        <button class="qp-ghost" @click="openView('speaklimit')">演示：跟读次数用完</button>
      </view>

      <view v-else-if="view === 'photolimit'" class="qp-page">
        <view class="qp-title">今天拍照用完了</view>
        <view class="qp-sub">先去闯关或听一听</view>
        <view class="qp-card">闯关和磨耳朵还能继续。这一页不显示价格。</view>
        <button class="qp-cta" @click="openView('battle')">去闯关</button>
        <button class="qp-ghost" @click="openView('listen')">听一听</button>
        <button class="qp-ghost" @click="openView('photogate')">告诉爸爸妈妈</button>
      </view>

      <view v-else-if="view === 'speaklimit'" class="qp-page">
        <view class="qp-title">今天跟读先听到这里</view>
        <view class="qp-sub">闯关和磨耳朵还能继续</view>
        <view class="qp-card">可以再听标准音。这一页不显示价格，也不能购买。</view>
        <button class="qp-cta" @click="openView('battle')">去闯关</button>
        <button class="qp-ghost" @click="openView('listen')">磨耳朵</button>
        <button class="qp-ghost" @click="openView('photogate')">告诉爸爸妈妈</button>
      </view>

      <view v-else-if="view === 'photogate'" class="qp-page">
        <view class="qp-title">请爸爸妈妈来</view>
        <view class="qp-sub">先做一道口算。这里没有价格。</view>
        <view class="qp-question">8 + 6 = ?</view>
        <view class="qp-choices">
          <button class="qp-choice" @click="answerGate(false)">12</button>
          <button class="qp-choice" @click="answerGate(true)">14</button>
          <button class="qp-choice" @click="answerGate(false)">15</button>
          <button class="qp-choice" @click="answerGate(false)">16</button>
        </view>
        <view v-if="play.gateOn" class="qp-hint">{{ play.gateText }}</view>
        <button class="qp-ghost" @click="openView('battle')">去闯关</button>
      </view>

      <view v-else-if="view === 'poet'" class="qp-page">
        <view class="qp-title">诗词</view>
        <view class="qp-sub">先按体裁找。课内的排在前面，不一次铺开很多首。</view>
        <view class="qp-pills">
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemForm === 'all' }" @click="setPoemForm('all')">全部</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemForm === 'wu' }" @click="setPoemForm('wu')">五言绝句</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemForm === 'qi' }" @click="setPoemForm('qi')">七言绝句</button>
        </view>
        <view v-if="poemVisible('wu')" class="qp-sub">五言绝句</view>
        <button v-for="poem in visiblePoems('wu')" :key="poem.id" class="qp-item" @click="openPoem(poem.id)">
          <b>{{ poem.title }}</b>
          <text class="qp-tiny">{{ poem.dynasty }} · {{ poem.author }} · {{ poem.tag }}</text>
        </button>
        <view v-if="poemVisible('qi')" class="qp-sub">七言绝句</view>
        <button v-for="poem in visiblePoems('qi')" :key="poem.id" class="qp-item" @click="openPoem(poem.id)">
          <b>{{ poem.title }}</b>
          <text class="qp-tiny">{{ poem.dynasty }} · {{ poem.author }} · {{ poem.tag }}</text>
        </button>
      </view>

      <view v-else-if="view === 'poem'" class="qp-page" :class="{ 'qp-py-off': !planet.poemPyOn }">
        <button class="qp-back" @click="openView('poet')">返回目录</button>
        <view class="qp-scene">
          <view class="qp-moon"></view>
          <view class="qp-poet" :class="{ 'is-talk': play.poetTalk }">{{ poem.face }}</view>
        </view>
        <view class="qp-poem-title">{{ poem.title }}</view>
        <view class="qp-source">{{ poem.dynasty }}：<button class="qp-link" @click="openAuthor">{{ poem.author }}</button></view>
        <view class="qp-row">
          <text class="qp-tiny">{{ poem.form }} · {{ poem.tag }}</text>
          <button class="qp-ghost" @click="togglePy">{{ planet.poemPyOn ? '注音开着' : '注音藏了' }}</button>
        </view>
        <button
          v-for="(line, index) in poem.lines"
          :key="line.text"
          class="qp-verse"
          :class="{ 'is-on': planet.poemLine === index }"
          @click="pickLine(index)"
        >
          <text class="qp-py">{{ line.py }}</text>
          <b>{{ line.text }}</b>
        </button>
        <view class="qp-hint">{{ poemSay }}</view>
        <view class="qp-pills">
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemLayer === 'yi' }" @click="setLayer('yi')">直译</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemLayer === 'zhu' }" @click="setLayer('zhu')">注释</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemLayer === 'bei' }" @click="setLayer('bei')">背景</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemLayer === 'shang' }" @click="setLayer('shang')">赏析</button>
          <button class="qp-chip-btn" :class="{ 'is-on': planet.poemLayer === 'ren' }" @click="setLayer('ren')">诗人</button>
        </view>
        <view v-if="planet.poemLayer === 'yi'" class="qp-card">{{ poem.yi }}</view>
        <view v-else-if="planet.poemLayer === 'zhu'" class="qp-card">
          <view class="qp-pills">
            <button v-for="note in line.notes" :key="note[0]" class="qp-chip-btn" @click="showNote(note)">{{ note[0] }}</button>
          </view>
          <view class="qp-sub">{{ noteText }}</view>
        </view>
        <view v-else-if="planet.poemLayer === 'bei'" class="qp-card">{{ poem.bei }}</view>
        <view v-else-if="planet.poemLayer === 'shang'" class="qp-card">{{ line.shang }}</view>
        <view v-else class="qp-card">{{ poem.ren }}</view>
        <view class="qp-row">
          <button class="qp-ghost" @click="sayPoem('read')">请他读</button>
          <button class="qp-ghost" @click="sayPoem('talk')">请他讲</button>
          <button class="qp-ghost" @click="sayPoem('together')">一起读</button>
        </view>
      </view>

      <view v-else-if="view === 'math'" class="qp-page">
        <view class="qp-title">数理工坊 · 口算</view>
        <view class="qp-sub">{{ math.follow }}</view>
        <view v-if="math.showModes" class="qp-pills">
          <button class="qp-chip-btn" :class="{ 'is-on': math.mode === 'oral' }" @click="setMathMode('oral')">口算</button>
          <button class="qp-chip-btn" :class="{ 'is-on': math.mode === 'vert' }" @click="setMathMode('vert')">竖式</button>
          <button class="qp-chip-btn" :class="{ 'is-on': math.mode === 'listen' }" @click="setMathMode('listen')">听算</button>
          <button class="qp-chip-btn" :class="{ 'is-on': math.mode === 'cmp' }" @click="setMathMode('cmp')">比大小</button>
          <button class="qp-chip-btn" :class="{ 'is-on': math.mode === 'unit' }" @click="setMathMode('unit')">厘米和米</button>
        </view>
        <view class="qp-sub">{{ math.progress }}</view>
        <view class="qp-bar"><i :style="{ width: math.bar + '%' }"></i></view>
        <view v-if="!math.hideFace && !math.showVert" class="qp-question">{{ math.question }}</view>
        <view v-if="math.pre" class="qp-choices">
          <button class="qp-choice" @click="submitPre(true)">🍎🍎🍎🍎🍎 更多</button>
          <button class="qp-choice" @click="submitPre(false)">🍎🍎🍎 少一些</button>
        </view>
        <view v-if="math.showVert" class="qp-vert">
          <view>{{ math.a }}</view>
          <view>{{ math.b }}</view>
          <view class="qp-vert-line"></view>
          <view>{{ math.answerShown }}</view>
        </view>
        <view v-if="math.hideFace" class="qp-hint">芽芽在读题。可以先答，也可以点「我听到了」再看题。</view>
        <view v-if="math.showPad" class="qp-digits">{{ math.digitText }}</view>
        <view class="qp-hint">{{ math.hint }}</view>
        <view v-if="math.showWhy" class="qp-pills">
          <button class="qp-chip-btn" @click="applyWhy('slip')">看错了</button>
          <button class="qp-chip-btn" @click="applyWhy('gap')">还不会</button>
          <button class="qp-chip-btn" @click="applyWhy('fast')">算太快</button>
        </view>
        <view v-if="math.showCmp" class="qp-choices">
          <button class="qp-choice" @click="submitMath('>')">大于</button>
          <button class="qp-choice" @click="submitMath('<')">小于</button>
          <button class="qp-choice" @click="submitMath('=')">等于</button>
        </view>
        <button v-if="math.showHeard" class="qp-ghost" @click="hearMath">我听到了</button>
        <view v-if="math.showPad" class="qp-pad">
          <button v-for="key in mathKeys" :key="key" class="qp-key" :class="{ 'qp-key-ok': key === 'ok' }" @click="pressMathKey(key)">{{ key === 'ok' ? '✓' : key === 'del' ? '删' : key }}</button>
        </view>
        <view class="qp-pills">
          <button class="qp-chip-btn" @click="buddyMath">和芽芽比一比</button>
          <button class="qp-chip-btn" @click="photoMath">拍下我写的</button>
          <button class="qp-chip-btn" @click="stopMath">先停在这里</button>
        </view>
        <button class="qp-ghost" @click="openView('points')">换个知识点</button>
        <button class="qp-ghost" @click="finishMathDemo">演示：这组做完</button>
      </view>

      <view v-else-if="view === 'mreport'" class="qp-page">
        <view class="qp-title">今天这组练完</view>
        <view class="qp-sub">{{ reportText() }}</view>
        <view class="qp-card">没有名次，也不和别的小朋友比。明天还练这个点。</view>
        <button class="qp-cta" @click="resetMath">再来一组</button>
        <button class="qp-ghost" @click="openView('home')">回星球</button>
      </view>

      <view v-else-if="view === 'points'" class="qp-page">
        <view class="qp-title">小学知识点</view>
        <view class="qp-sub">{{ info.point }}</view>
        <button class="qp-item" :class="{ 'is-pick': pointOn('main') }" @click="setMathPoint('main', '当前跟进：退位减法。', true)">
          <b>退位减法</b><text class="qp-tiny">二年级 · 当前跟进</text>
        </button>
        <button class="qp-item" :class="{ 'is-pick': pointOn('fallback') }" @click="setMathPoint('fallback', '先回去修 20 以内退位。今天这组换成更小的数。', true)">
          <b>20 以内退位</b><text class="qp-tiny">一年级 · 建议回退</text>
        </button>
        <button class="qp-item" @click="setMathPoint('later', '表内乘法已经较熟，今天先不加新题。', false)">
          <b>表内乘法</b><text class="qp-tiny">二年级 · 已掌握</text>
        </button>
        <button class="qp-item" @click="setMathPoint('later', '分数加法还没学。不会跳到三年级。', false)">
          <b>分数加法</b><text class="qp-tiny">三年级 · 未学</text>
        </button>
      </view>

      <view v-else-if="view === 'spell' && play.spell" class="qp-page">
        <view class="qp-row">
          <button class="qp-back" @click="openView('map')">返回</button>
          <text v-if="!play.spell.done" class="qp-chip">{{ play.spell.index + 1 }} / {{ play.spell.queue.length }}</text>
        </view>
        <view v-if="play.spell.done">
          <view class="qp-title">{{ play.spell.mode === 'sound' ? '这组听写完了' : '这组拼完了' }}</view>
          <view class="qp-sub">一次拼对 {{ play.spell.got.length }} 个</view>
          <view class="qp-card">
            <view class="qp-sub">一次拼对</view>
            <view class="qp-pills">
              <text v-if="play.spell.got.length === 0" class="qp-pill">这次还没有一次拼对</text>
              <text v-for="word in play.spell.got" :key="word" class="qp-pill">{{ word }}</text>
            </view>
            <view class="qp-sub">还要再拼</view>
            <view class="qp-pills">
              <text v-if="play.spell.missed.length === 0" class="qp-pill">没有放错的词</text>
              <text v-for="word in play.spell.missed" :key="word" class="qp-pill is-warn">{{ word }}</text>
            </view>
          </view>
          <button class="qp-cta" @click="againSpell()">{{ play.spell.mode === 'sound' ? '再听一组' : '再拼一组' }}</button>
          <button class="qp-ghost" @click="openView('book')">回单词本</button>
        </view>
        <view v-else>
          <view v-if="play.spell.mode === 'sound'" class="qp-spell-hear">
            <button class="qp-cta" @click="replaySpell">再听一次</button>
            <view class="qp-sub">听读音，把单词拼出来。先不看这个词。</view>
          </view>
          <view v-else>
            <view class="qp-cam-shot">{{ play.spell.queue[play.spell.index].emoji }}</view>
            <view class="qp-question">{{ play.spell.queue[play.spell.index].zh }}</view>
            <view v-if="play.spell.showWord" class="qp-sub">看着拼：{{ play.spell.queue[play.spell.index].en }}</view>
          </view>
          <view class="qp-spell-slots">
            <button
              v-for="(slot, index) in play.spell.slots"
              :key="index"
              class="qp-spell-slot"
              :class="{ 'is-lock': slot.lock, 'is-bad': play.spell.bad }"
              @click="undoSpell(index)"
            >{{ slot.letter }}</button>
          </view>
          <view class="qp-hint">{{ play.spell.message }}</view>
          <view class="qp-spell-bank" :class="{ 'is-keys': play.spell.freeType }">
            <button
              v-for="tile in play.spell.bank"
              :key="tile.id"
              class="qp-spell-key"
              :class="{ 'is-used': tile.used }"
              @click="pressSpell(tile.id)"
            >{{ tile.letter }}</button>
          </view>
          <button class="qp-ghost" @click="hintSpell">看一个字母</button>
        </view>
      </view>

      <view v-else-if="view === 'listen'" class="qp-page">
        <view class="qp-title">磨耳朵电台</view>
        <button class="qp-card" @click="toggleListen">
          🍳 厨房里的苹果
          <text class="qp-tiny">{{ play.listenOn ? '正在播放 · 可跟读' : '1:20 / 2:46 · 可跟读' }}</text>
        </button>
        <button class="qp-ghost" @click="startSpell()">拼一拼</button>
        <button class="qp-ghost" @click="startHearSpell()">听音写</button>
        <button class="qp-cta" @click="openView('home')">听完回星球</button>
      </view>

      <view v-else class="qp-page">
        <view class="qp-title">成长舱</view>
        <view class="qp-card">{{ info.mine }}</view>
        <button class="qp-item" @click="openView('book')">单词本 · 18 个新词</button>
        <button class="qp-item" @click="openView('points')">数学跟进 · {{ planet.math.bank === 'small' ? '20 以内退位' : '退位减法' }}</button>
        <view class="qp-hint">年级在这里不能改。请爸爸妈妈从下面进入。</view>
        <button class="qp-cta" @click="openParent('phome')">爸爸妈妈从这里进</button>
      </view>
    </scroll-view>
    <view v-if="play.speechAsk && play.speech" class="qp-speech-mask">
      <view class="qp-title">请爸爸妈妈来点</view>
      <view class="qp-sub">同意后，这一小段录音会发给口评服务，只用来判断读得像不像。</view>
      <view class="qp-card">不保存成声纹，页面上也不显示分数，也不出现价格。现在要读：{{ play.speech.refText }}</view>
      <button class="qp-cta" @click="allowSpeak">爸爸妈妈同意</button>
      <button class="qp-ghost" @click="denySpeak">先不上传，只听标准音</button>
    </view>
    <view v-else-if="play.speechOn && play.speech" class="qp-speech-mask">
      <view class="qp-row">
        <button class="qp-back" @click="closeSpeech">返回</button>
        <text class="qp-chip">还可跟读 {{ planet.speakLeft }} 次</text>
      </view>
      <view v-if="play.speech.phase === 'limit'" class="qp-page">
        <view class="qp-title">今天跟读先听到这里</view>
        <view class="qp-sub">闯关和磨耳朵还能继续。这一页不显示价格。</view>
        <button class="qp-cta" @click="playSpeechSample">再听标准音</button>
        <button class="qp-ghost" @click="closeSpeech">先回去</button>
      </view>
      <view v-else class="qp-page">
        <view class="qp-sub">{{ play.speech.zh }} {{ play.speech.ipa }}</view>
        <view class="qp-question">{{ play.speech.refText }}</view>
        <view v-if="play.speech.sentence" class="qp-pills">
          <button class="qp-chip-btn" :class="{ 'is-on': play.speech.piece !== 'sentence' }" @click="setSpeechPiece('word')">读单词</button>
          <button class="qp-chip-btn" :class="{ 'is-on': play.speech.piece === 'sentence' }" @click="setSpeechPiece('sentence')">读这一句</button>
        </view>
        <view v-if="play.speech.phase === 'recording'" class="qp-hint">正在听 {{ play.speech.seconds }} 秒。松开就结束。</view>
        <view v-else-if="play.speech.phase === 'opening'" class="qp-hint">麦克风准备中，请先不要松开。</view>
        <view v-else-if="play.speech.phase === 'assessing'" class="qp-hint">芽芽在听你读得像不像。</view>
        <view v-else-if="play.speech.phase === 'result' && play.speech.status === 'scored'">
          <view class="qp-stars">{{ starLine(play.speech.stars) }}</view>
          <view class="qp-title">{{ play.speech.label }}</view>
          <view class="qp-sub">{{ play.speech.hint }}</view>
          <button v-if="play.speech.stars === 0 && !play.speech.inReview" class="qp-cta" @click="keepSpeechReview">放进复习</button>
          <view v-if="play.speech.stars === 0 && play.speech.inReview" class="qp-hint">已经在复习里。单词本的困难词可以再读。</view>
          <button v-if="play.speech.stars > 0 && play.speech.inReview" class="qp-ghost" @click="dropReview(play.speech.reviewId)">读对了，移出复习</button>
        </view>
        <view v-else-if="play.speech.phase === 'result'">
          <view class="qp-title">这次先不打星</view>
          <view class="qp-sub">{{ play.speech.hint }}</view>
        </view>
        <view v-else class="qp-hint">按住橙色按钮读，松开就结束。三星是完美，两星是中等，一星是一般，没有星就是还不准确。</view>
        <button
          class="qp-rec"
          hover-class="none"
          :class="{ 'is-on': play.speech.phase === 'recording', 'is-wait': play.speech.phase === 'assessing' }"
          @touchstart.stop.prevent="pressSpeech"
          @touchmove.stop.prevent="keepSpeechHold"
          @touchend.stop.prevent="releaseSpeech"
          @touchcancel.stop.prevent="releaseSpeech"
          @mousedown.stop.prevent="pressSpeech"
          @mouseup.stop.prevent="releaseSpeech"
          @contextmenu.prevent="keepSpeechHold"
        >{{ speechHoldText() }}</button>
        <button class="qp-ghost" @click="playSpeechSample">听标准音</button>
      </view>
    </view>
    <view v-if="play.toastOn" class="qp-toast">{{ play.toast }}</view>
  </view>
</template>

<script setup>
import { computed, ref } from "vue";
import {
  answerBattle,
  answerGate,
  applyWhy,
  bagCount,
  bookWords,
  buddyMath,
  choiceClass,
  continueToday,
  currentLine,
  currentPhoto,
  currentPoem,
  currentQuestion,
  finishMathDemo,
  gotWords,
  gradeInfo,
  hearMath,
  homeOf,
  mathView,
  missedWords,
  onWorld,
  openParent,
  openPoem,
  openView,
  photoCards,
  photoMath,
  pickCand,
  pickLine,
  planet,
  play,
  playSound,
  playStandard,
  poemVisible,
  poems,
  pointOn,
  pressMathKey,
  readPoem,
  reportText,
  savedPhotoWord,
  setBookTab,
  setLayer,
  setMathMode,
  setMathPoint,
  setPoemForm,
  shootPhoto,
  starLine,
  startBattle,
  startSpell,
  startHearSpell,
  againSpell,
  replaySpell,
  canSpell,
  hintSpell,
  pressSpell,
  undoSpell,
  stopMath,
  submitMath,
  submitPre,
  tabOf,
  toggleFlash,
  toggleListen,
  togglePy,
  openAuthor,
  blurPhoto,
  cancelPhoto,
  againReview,
  allowSpeak,
  closeSpeech,
  denySpeak,
  dropReview,
  followRead,
  keepPhoto,
  keepSpeechReview,
  playSpeechSample,
  setSpeechPiece,
  pressSpeech,
  releaseSpeech,
  readyPhoto,
  resetMath,
  worlds,
} from "@/planet/state.js";

const props = defineProps({
  tab: { type: String, default: "home" },
});

const noteText = ref("点一个字，只看这一个字。");
const poemTalk = ref("");
const mathKeys = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "del", "0", "ok"];

const info = computed(() => gradeInfo());
const view = computed(() => (tabOf(play.view) === props.tab ? play.view : homeOf(props.tab)));
const question = computed(() => currentQuestion());
const photo = computed(() => currentPhoto());
const saved = computed(() => savedPhotoWord());
const poem = computed(() => currentPoem());
const line = computed(() => currentLine());
const math = computed(() => mathView());
const worldList = computed(() => worlds());
const starWords = computed(() => {
  if (play.stars >= 3) return "三颗星";
  if (play.stars === 2) return "两颗星";
  return "一颗星";
});
const poemSay = computed(() => {
  if (poemTalk.value) return poemTalk.value;
  return "点这一句，" + poem.value.author + "只读这一句";
});

function visiblePoems(formKey) {
  if (!poemVisible(formKey)) return [];
  const list = poems();
  const out = [];
  for (let i = 0; i < list.length; i += 1) {
    if (list[i].formKey === formKey) out.push(list[i]);
  }
  return out;
}

function showNote(note) {
  noteText.value = note[0] + "：" + note[1];
}

function sayPoem(kind) {
  poemTalk.value = readPoem(kind);
}

function speechHoldText() {
  const phase = play.speech ? play.speech.phase : "";
  if (phase === "recording") return "松开";
  if (phase === "opening") return "听着";
  if (phase === "assessing") return "等一下";
  return "按住读";
}

function keepSpeechHold() {}
</script>