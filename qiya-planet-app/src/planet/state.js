import { reactive } from "vue";
import {
  BATTLE_QUESTIONS,
  MATH_MAIN,
  MATH_SMALL,
  PHOTO_TOTAL,
  PHOTO_WORDS,
  SPEAK_TOTAL,
  SPELL_WORDS,
  UNIT_SET,
  WORLDS,
  lessonById,
  lessonsForGrade,
  makeRepair,
  poemById,
  redoCompare,
  redoItem,
  redoUnit,
} from "./content.js";
import { GRADE_ORDER, GRADES } from "./grades.js";
import { assessSpeech } from "./speech-api.js";
import { beginRecording, discardRecording, finishRecording } from "./recorder.js";

const STORAGE_KEY = "qiya-planet-app-v1";

const TAB_ROUTE = {
  home: "pages/planet/home/index",
  english: "pages/planet/english/index",
  poem: "pages/planet/poem/index",
  math: "pages/planet/math/index",
};

const HOME_VIEWS = ["home", "listen", "mine"];
const ENGLISH_VIEWS = ["map", "battle", "settle", "book", "spell", "photocam", "photolook", "photopick", "photoblur", "photo", "photolimit", "speaklimit", "photogate"];
const POEM_VIEWS = ["poet", "poem"];
const MATH_VIEWS = ["math", "mreport", "points"];

export const planet = reactive({
  gradeKey: "g12",
  streak: 5,
  photoLeft: 1,
  speakLeft: SPEAK_TOTAL,
  speakDay: "",
  speechReviews: [],
  lastSpeech: null,
  photoPick: "cup",
  savedPhoto: "cup",
  bookTab: "learning",
  poemId: "jing",
  poemLine: 0,
  poemLayer: "yi",
  poemPyOn: true,
  poemForm: "all",
  flashOn: false,
  settings: {
    limit: true,
    mnemonic: true,
    photo: true,
    night: true,
    bottle: true,
    speak: false,
  },
  math: {
    mode: "oral",
    bank: "main",
    index: 0,
    correct: 0,
    streak: 0,
    gapStreak: 0,
    heard: false,
    repair: null,
    awaitingWhy: false,
    digits: "",
    unitIndex: 0,
    preMiss: false,
    desk: "oral",
    reviewDay: "",
    reviewDigits: "",
    reviewMiss: false,
    learn: {
      lessonId: "",
      day: "",
      step: 0,
      frame: 0,
      drill: 0,
      digits: "",
      miss: false,
      finished: false,
      lastGuess: "",
    },
    wrongs: [],
  },
});

export const play = reactive({
  view: "home",
  parentView: "phome",
  toast: "",
  toastOn: false,
  battleIndex: 0,
  battlePick: -1,
  battleHintOn: false,
  battleHint: "",
  stars: 2,
  got: [],
  missed: [],
  listenOn: false,
  poetTalk: false,
  gateOn: false,
  gateText: "",
  speechOn: false,
  speechAsk: false,
  speech: null,
  spell: null,
});

let booted = false;
let toastTimer = 0;
let photoTimer = 0;
let battleTimer = 0;
let spellTimer = 0;
let spellAudio = null;
let poetTimer = 0;

function readSaved() {
  try {
    const raw = uni.getStorageSync(STORAGE_KEY);
    if (!raw) return null;
    if (typeof raw === "string") return JSON.parse(raw);
    return raw;
  } catch (err) {
    return null;
  }
}

function savePlanet() {
  const math = planet.math;
  uni.setStorageSync(STORAGE_KEY, {
    gradeKey: planet.gradeKey,
    photoLeft: planet.photoLeft,
    speakLeft: planet.speakLeft,
    speakDay: planet.speakDay,
    speechReviews: planet.speechReviews,
    lastSpeech: planet.lastSpeech,
    photoPick: planet.photoPick,
    savedPhoto: planet.savedPhoto,
    bookTab: planet.bookTab,
    poemId: planet.poemId,
    poemPyOn: planet.poemPyOn,
    poemForm: planet.poemForm,
    flashOn: planet.flashOn,
    settings: {
      limit: planet.settings.limit,
      mnemonic: planet.settings.mnemonic,
      photo: planet.settings.photo,
      night: planet.settings.night,
      bottle: planet.settings.bottle,
      speak: planet.settings.speak,
    },
    math: {
      mode: math.mode,
      bank: math.bank,
      index: math.index,
      correct: math.correct,
      streak: math.streak,
      gapStreak: math.gapStreak,
      unitIndex: math.unitIndex,
      desk: math.desk || "oral",
      reviewDay: math.reviewDay || "",
      learn: {
        lessonId: math.learn.lessonId,
        day: math.learn.day,
        step: math.learn.step,
        frame: math.learn.frame,
        drill: math.learn.drill,
        finished: !!math.learn.finished,
      },
      wrongs: math.wrongs || [],
    },
  });
}

export function boot() {
  if (booted) return;
  booted = true;
  const saved = readSaved();
  if (!saved) return;
  if (saved.gradeKey && GRADES[saved.gradeKey]) planet.gradeKey = saved.gradeKey;
  if (typeof saved.photoLeft === "number") planet.photoLeft = saved.photoLeft;
  if (typeof saved.speakLeft === "number") planet.speakLeft = saved.speakLeft;
  if (typeof saved.speakDay === "string") planet.speakDay = saved.speakDay;
  if (saved.speechReviews) planet.speechReviews = cleanReviews(saved.speechReviews);
  if (saved.lastSpeech && saved.lastSpeech.title && saved.lastSpeech.label) {
    planet.lastSpeech = {
      title: String(saved.lastSpeech.title),
      label: String(saved.lastSpeech.label),
      hint: saved.lastSpeech.hint ? String(saved.lastSpeech.hint) : "",
    };
  }
  if (saved.photoPick && PHOTO_WORDS[saved.photoPick]) planet.photoPick = saved.photoPick;
  if (saved.savedPhoto && PHOTO_WORDS[saved.savedPhoto]) planet.savedPhoto = saved.savedPhoto;
  if (saved.bookTab) planet.bookTab = saved.bookTab;
  if (saved.poemId) planet.poemId = saved.poemId;
  if (typeof saved.poemPyOn === "boolean") planet.poemPyOn = saved.poemPyOn;
  if (saved.poemForm) planet.poemForm = saved.poemForm;
  if (typeof saved.flashOn === "boolean") planet.flashOn = saved.flashOn;
  if (saved.settings) {
    planet.settings.limit = !!saved.settings.limit;
    planet.settings.mnemonic = !!saved.settings.mnemonic;
    planet.settings.photo = !!saved.settings.photo;
    planet.settings.night = !!saved.settings.night;
    planet.settings.bottle = !!saved.settings.bottle;
    planet.settings.speak = !!saved.settings.speak;
  }
  if (saved.math) {
    const math = planet.math;
    if (saved.math.mode) math.mode = saved.math.mode;
    if (saved.math.bank) math.bank = saved.math.bank;
    if (typeof saved.math.index === "number") math.index = saved.math.index;
    if (typeof saved.math.correct === "number") math.correct = saved.math.correct;
    if (typeof saved.math.streak === "number") math.streak = saved.math.streak;
    if (typeof saved.math.gapStreak === "number") math.gapStreak = saved.math.gapStreak;
    if (typeof saved.math.unitIndex === "number") math.unitIndex = saved.math.unitIndex;
    if (!math.learn) math.learn = defaultLearn();
    if (saved.math.learn) {
      const src = saved.math.learn;
      if (src.lessonId) math.learn.lessonId = String(src.lessonId);
      if (src.day) math.learn.day = String(src.day);
      if (typeof src.step === "number") math.learn.step = src.step;
      if (typeof src.frame === "number") math.learn.frame = src.frame;
      if (typeof src.drill === "number") math.learn.drill = src.drill;
      if (typeof src.finished === "boolean") math.learn.finished = src.finished;
    }
    math.wrongs = cleanWrongs(saved.math.wrongs);
    if (saved.math.desk === "learn" || saved.math.desk === "review" || saved.math.desk === "oral") math.desk = saved.math.desk;
    if (typeof saved.math.reviewDay === "string") math.reviewDay = saved.math.reviewDay;
  }
  refreshSpeakQuota();
}

export function toast(message) {
  play.toast = message;
  play.toastOn = true;
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => {
    play.toastOn = false;
  }, 1700);
}

export function gradeInfo() {
  return GRADES[planet.gradeKey] || GRADES.g12;
}

export function tabOf(name) {
  if (ENGLISH_VIEWS.indexOf(name) >= 0) return "english";
  if (POEM_VIEWS.indexOf(name) >= 0) return "poem";
  if (MATH_VIEWS.indexOf(name) >= 0) return "math";
  if (HOME_VIEWS.indexOf(name) >= 0) return "home";
  return "home";
}

export function homeOf(tab) {
  if (tab === "english") return planet.gradeKey === "pre" ? "listen" : "map";
  if (tab === "poem") return "poet";
  if (tab === "math") return "math";
  return "home";
}

export function ensureTab(tab) {
  if (tabOf(play.view) !== tab) play.view = homeOf(tab);
  if (tab === "math") prepareMathDesk();
}

export function openView(name) {
  if (name !== "spell") stopSpellAudio();
  if (name === "math") prepareMathDesk();
  play.view = name;
  const target = TAB_ROUTE[tabOf(name)];
  const pages = getCurrentPages();
  const last = pages.length ? pages[pages.length - 1] : null;
  const route = last && last.route ? last.route : "";
  if (route !== target) uni.switchTab({ url: "/" + target });
}

export function openParent(name) {
  play.parentView = name || "phome";
  const pages = getCurrentPages();
  const last = pages.length ? pages[pages.length - 1] : null;
  const route = last && last.route ? last.route : "";
  if (route !== "pages/planet/parent/index") {
    uni.navigateTo({ url: "/pages/planet/parent/index" });
  }
}

export function currentQuestion() {
  const index = play.battleIndex >= BATTLE_QUESTIONS.length ? BATTLE_QUESTIONS.length - 1 : play.battleIndex;
  return BATTLE_QUESTIONS[index];
}

export function startBattle(reset) {
  if (reset) {
    clearTimeout(battleTimer);
    play.battleIndex = 0;
    play.battlePick = -1;
    play.battleHintOn = false;
    play.battleHint = "";
    play.got = [];
    play.missed = [];
    play.stars = 2;
  }
  openView("battle");
}

export function playSound() {
  toast(currentQuestion().sound);
}

export function answerBattle(index) {
  const question = currentQuestion();
  const choice = question.choices[index];
  if (!choice) return;
  const ok = !!choice.ok;
  play.battlePick = index;
  play.battleHintOn = true;
  play.battleHint = ok ? question.shell : question.repair;
  if (!ok) {
    if (play.missed.indexOf(question.focus) < 0) play.missed.push(question.focus);
    toast(question.repair);
    return;
  }
  if (play.got.indexOf(question.focus) < 0) play.got.push(question.focus);
  const last = play.battleIndex >= BATTLE_QUESTIONS.length - 1;
  clearTimeout(battleTimer);
  battleTimer = setTimeout(() => {
    if (play.view !== "battle") return;
    if (last) {
      play.stars = play.got.length >= 4 ? 3 : play.got.length >= 2 ? 2 : 1;
      play.view = "settle";
      return;
    }
    play.battleIndex += 1;
    play.battlePick = -1;
    play.battleHintOn = false;
    play.battleHint = "";
  }, 450);
}

export function choiceClass(index) {
  if (play.battlePick !== index) return "";
  const choice = currentQuestion().choices[index];
  if (!choice) return "";
  return choice.ok ? "is-right" : "is-wrong";
}

export function bagCount() {
  return play.missed.length > 0 ? play.missed.length : 1;
}

export function starLine(count) {
  let text = "";
  for (let i = 0; i < 3; i += 1) text += i < count ? "★ " : "☆ ";
  return text.trim();
}

export function onWorld(world) {
  if (!world) return;
  if (world.state === "current") {
    openView("battle");
    return;
  }
  toast(world.act || "先把好吃的闯完。");
}

export function worlds() {
  return WORLDS;
}

export function setBookTab(tab) {
  planet.bookTab = tab;
  savePlanet();
}

export function bookWords() {
  const saved = PHOTO_WORDS[planet.savedPhoto] || PHOTO_WORDS.cup;
  const all = [
    { tab: "learning", en: "apple", note: "苹果 · 灯 2/3" },
    { tab: "learning", en: saved.en, note: saved.zh + " · 拍照词" },
    { tab: "learning", en: "plate", note: "盘子 · 闯关中" },
    { tab: "photo", en: saved.en, note: saved.zh + " · 今天拍的" },
    { tab: "photo", en: "lamp", note: "昨天 · 台灯" },
    { tab: "hard", en: "fork", note: "错 2 次 · 明天复习" },
  ];
  const reviews = planet.speechReviews || [];
  for (let i = 0; i < reviews.length; i += 1) {
    const item = reviews[i];
    all.push({
      tab: "hard",
      en: item.title,
      note: (item.zh ? item.zh + " · " : "") + "读音不准确 · 自己放进复习",
      reviewId: item.id,
    });
  }
  all.push({ tab: "done", en: "cat", note: "已点亮" });
  const list = [];
  for (let i = 0; i < all.length; i += 1) {
    if (all[i].tab === planet.bookTab) list.push(all[i]);
  }
  return list;
}

function shuffleLetters(list) {
  const copy = list.slice();
  for (let i = copy.length - 1; i > 0; i -= 1) {
    const j = Math.floor(Math.random() * (i + 1));
    const tmp = copy[i];
    copy[i] = copy[j];
    copy[j] = tmp;
  }
  return copy;
}

function extraLetters(word, count) {
  const alphabet = "abcdefghijklmnopqrstuvwxyz";
  const used = {};
  const pool = [];
  for (let i = 0; i < word.length; i += 1) used[word.charAt(i)] = true;
  for (let i = 0; i < alphabet.length; i += 1) {
    if (!used[alphabet.charAt(i)]) pool.push(alphabet.charAt(i));
  }
  const shuffled = shuffleLetters(pool);
  const out = [];
  for (let i = 0; i < count && i < shuffled.length; i += 1) out.push(shuffled[i]);
  return out;
}

function spellPool() {
  const list = [];
  const seen = {};
  for (let i = 0; i < SPELL_WORDS.length; i += 1) {
    list.push(SPELL_WORDS[i]);
    seen[SPELL_WORDS[i].en] = true;
  }
  const saved = PHOTO_WORDS[planet.savedPhoto];
  if (saved && !seen[saved.en]) list.unshift({ en: saved.en, zh: saved.zh, emoji: saved.emoji });
  return list;
}

function findSpellItem(en) {
  const pool = spellPool();
  for (let i = 0; i < pool.length; i += 1) {
    if (pool[i].en === en) return pool[i];
  }
  return { en: en, zh: "这个词", emoji: "✏️" };
}

function stopSpellAudio() {
  if (typeof window !== "undefined" && window.speechSynthesis) {
    try {
      window.speechSynthesis.cancel();
    } catch (err) {
      /* 停止上一次朗读失败时，继续播这一次 */
    }
  }
  if (!spellAudio) return;
  try {
    if (spellAudio.stop) spellAudio.stop();
    if (spellAudio.pause) spellAudio.pause();
    if (spellAudio.destroy) spellAudio.destroy();
  } catch (err) {
    /* 关掉上一段读音失败也不影响下一题 */
  }
  spellAudio = null;
}

function englishVoice() {
  if (typeof window === "undefined" || !window.speechSynthesis || !window.speechSynthesis.getVoices) return null;
  const voices = window.speechSynthesis.getVoices();
  for (let i = 0; i < voices.length; i += 1) {
    const lang = String(voices[i].lang || "").toLowerCase();
    if (lang.indexOf("en") === 0) return voices[i];
  }
  return null;
}

function speakWithSynth(text) {
  if (typeof window === "undefined" || !window.speechSynthesis || !window.SpeechSynthesisUtterance) {
    toast("这台设备暂时读不出来。可以先看意思拼。");
    return;
  }
  const utter = new window.SpeechSynthesisUtterance(text);
  utter.lang = "en-US";
  utter.rate = 0.82;
  const voice = englishVoice();
  if (voice) utter.voice = voice;
  window.speechSynthesis.cancel();
  window.speechSynthesis.speak(utter);
}

function playDictAudio(text) {
  const url = "https://dict.youdao.com/dictvoice?audio=" + encodeURIComponent(text) + "&type=2";
  let fell = false;
  function fail() {
    if (fell) return;
    fell = true;
    speakWithSynth(text);
  }
  if (typeof Audio !== "undefined") {
    stopSpellAudio();
    const audio = new Audio(url);
    spellAudio = audio;
    audio.onerror = fail;
    const played = audio.play();
    if (played && played.catch) played.catch(fail);
    return true;
  }
  if (typeof uni !== "undefined" && uni.createInnerAudioContext) {
    stopSpellAudio();
    const audio = uni.createInnerAudioContext();
    spellAudio = audio;
    audio.src = url;
    audio.onError(fail);
    audio.play();
    return true;
  }
  return false;
}

function speakEnglish(word) {
  const text = String(word || "").toLowerCase();
  if (!text) return;
  if (englishVoice()) {
    stopSpellAudio();
    speakWithSynth(text);
    return;
  }
  if (playDictAudio(text)) return;
  speakWithSynth(text);
}

function loadSpellRound(spell) {
  const item = spell.queue[spell.index];
  const word = item.en.toLowerCase();
  const sound = spell.mode === "sound";
  const showWord = !sound && planet.gradeKey === "pre";
  const lockFirst = !sound && planet.gradeKey === "g12" && word.length > 1;
  const freeType = sound && (planet.gradeKey === "g36" || planet.gradeKey === "mid");
  const bank = [];
  if (freeType) {
    const alphabet = "abcdefghijklmnopqrstuvwxyz";
    for (let i = 0; i < alphabet.length; i += 1) {
      bank.push({ id: "key-" + alphabet.charAt(i), letter: alphabet.charAt(i), used: false });
    }
  } else {
    const extras = showWord ? 0 : 2;
    const tiles = [];
    for (let i = 0; i < word.length; i += 1) tiles.push({ id: spell.index + "-" + i, letter: word.charAt(i), used: false });
    const more = extraLetters(word, extras);
    for (let i = 0; i < more.length; i += 1) tiles.push({ id: spell.index + "-x" + i, letter: more[i], used: false });
    const shuffled = shuffleLetters(tiles);
    for (let i = 0; i < shuffled.length; i += 1) bank.push(shuffled[i]);
  }
  const slots = [];
  for (let i = 0; i < word.length; i += 1) slots.push({ letter: "", lock: false, tileId: "" });
  if (lockFirst) {
    for (let i = 0; i < bank.length; i += 1) {
      if (bank[i].letter === word.charAt(0)) {
        bank[i].used = true;
        slots[0] = { letter: word.charAt(0), lock: true, tileId: bank[i].id };
        break;
      }
    }
  }
  spell.slots = slots;
  spell.bank = bank;
  spell.freeType = freeType;
  spell.showWord = showWord;
  spell.hintUsed = false;
  spell.tried = false;
  spell.status = "";
  spell.bad = false;
  spell.message = sound ? "先听读音，再把听到的词拼出来。" : showWord ? "看着单词，按顺序点字母。" : lockFirst ? "第一个字母已经放好了。把剩下的拼完。" : "看意思，把字母拼成单词。";
  if (sound) speakEnglish(word);
}

function spellFilled(spell) {
  for (let i = 0; i < spell.slots.length; i += 1) {
    if (!spell.slots[i].letter) return false;
  }
  return spell.slots.length > 0;
}

function spellJoined(spell) {
  let text = "";
  for (let i = 0; i < spell.slots.length; i += 1) text += spell.slots[i].letter;
  return text;
}

function advanceSpell() {
  const spell = play.spell;
  if (!spell) return;
  if (spell.index >= spell.queue.length - 1) {
    spell.done = true;
    spell.message = "这组拼完了。";
    return;
  }
  spell.index += 1;
  loadSpellRound(spell);
}

function judgeSpell() {
  const spell = play.spell;
  if (!spell || spell.done) return;
  const item = spell.queue[spell.index];
  const ok = spellJoined(spell) === item.en.toLowerCase();
  if (ok) {
    spell.status = "ok";
    spell.bad = false;
    if (spell.tried) spell.message = "改对了。这个词还要再见一次。";
    else {
      spell.message = "拼对了。";
      if (spell.got.indexOf(item.en) < 0) spell.got.push(item.en);
    }
    clearTimeout(spellTimer);
    spellTimer = setTimeout(() => {
      if (!play.spell || play.view !== "spell") return;
      advanceSpell();
    }, 700);
    return;
  }
  spell.tried = true;
  spell.status = "bad";
  spell.bad = true;
  spell.message = spell.mode === "sound" ? "有字母放错了。它的意思是" + item.zh + "。点格子可以拿下来。" : "有字母放错了。点上面的格子可以拿下来。";
  if (spell.missed.indexOf(item.en) < 0) spell.missed.push(item.en);
}

export function canSpell(en) {
  return /^[a-zA-Z]+$/.test(String(en || ""));
}

export function startSpell(en, mode) {
  clearTimeout(spellTimer);
  const queue = [];
  const seen = {};
  const wanted = en ? String(en).toLowerCase() : "";
  if (wanted && canSpell(wanted)) {
    queue.push(findSpellItem(wanted));
    seen[wanted] = true;
  }
  const pool = spellPool();
  for (let i = 0; i < pool.length; i += 1) {
    if (seen[pool[i].en]) continue;
    queue.push(pool[i]);
    seen[pool[i].en] = true;
    if (queue.length >= 4) break;
  }
  if (!queue.length) {
    toast("还没有可以拼的单词");
    return;
  }
  const spell = {
    queue: queue,
    index: 0,
    slots: [],
    bank: [],
    status: "",
    message: "",
    got: [],
    missed: [],
    hintUsed: false,
    showWord: false,
    done: false,
    bad: false,
    tried: false,
    mode: mode === "sound" ? "sound" : "mean",
    freeType: false,
  };
  loadSpellRound(spell);
  play.spell = spell;
  openView("spell");
}

export function pressSpell(id) {
  const spell = play.spell;
  if (!spell || spell.done || spell.status === "ok") return;
  let tile = null;
  for (let i = 0; i < spell.bank.length; i += 1) {
    if (spell.bank[i].id === id) tile = spell.bank[i];
  }
  if (!tile || (tile.used && !spell.freeType)) return;
  let slot = null;
  for (let i = 0; i < spell.slots.length; i += 1) {
    if (!spell.slots[i].letter) {
      slot = spell.slots[i];
      break;
    }
  }
  if (!slot) return;
  if (!spell.freeType) tile.used = true;
  slot.letter = tile.letter;
  slot.tileId = tile.id;
  spell.bad = false;
  if (spellFilled(spell)) judgeSpell();
}

export function replaySpell() {
  const spell = play.spell;
  if (!spell || spell.done || spell.mode !== "sound") return;
  speakEnglish(spell.queue[spell.index].en);
}

export function startHearSpell(en) {
  startSpell(en || "", "sound");
}

export function againSpell() {
  const mode = play.spell && play.spell.mode === "sound" ? "sound" : "mean";
  startSpell("", mode);
}

export function undoSpell(index) {
  const spell = play.spell;
  if (!spell || spell.done || spell.status === "ok") return;
  const slot = spell.slots[index];
  if (!slot || slot.lock || !slot.letter) return;
  for (let i = 0; i < spell.bank.length; i += 1) {
    if (spell.bank[i].id === slot.tileId) spell.bank[i].used = false;
  }
  slot.letter = "";
  slot.tileId = "";
  spell.bad = false;
  spell.status = "";
}

export function hintSpell() {
  const spell = play.spell;
  if (!spell || spell.done || spell.status === "ok") return;
  if (spell.hintUsed) {
    spell.message = "这个词只能看一个字母。";
    return;
  }
  const word = spell.queue[spell.index].en.toLowerCase();
  let slotIndex = -1;
  for (let i = 0; i < spell.slots.length; i += 1) {
    if (!spell.slots[i].letter) {
      slotIndex = i;
      break;
    }
  }
  if (slotIndex < 0) {
    spell.message = "格子已经满了。先点掉一个再看提示。";
    return;
  }
  const need = word.charAt(slotIndex);
  let tile = null;
  for (let i = 0; i < spell.bank.length; i += 1) {
    if (!spell.bank[i].used && spell.bank[i].letter === need) {
      tile = spell.bank[i];
      break;
    }
  }
  if (!tile) {
    spell.message = "这个字母已经用过了。先把放错的拿下来。";
    return;
  }
  if (!spell.freeType) tile.used = true;
  spell.slots[slotIndex].letter = need;
  spell.slots[slotIndex].tileId = tile.id;
  spell.slots[slotIndex].lock = true;
  spell.hintUsed = true;
  spell.bad = false;
  spell.message = "帮你放了一个字母。";
  if (spellFilled(spell)) judgeSpell();
}

export function currentPhoto() {
  return PHOTO_WORDS[planet.photoPick] || PHOTO_WORDS.cup;
}

export function savedPhotoWord() {
  return PHOTO_WORDS[planet.savedPhoto] || PHOTO_WORDS.cup;
}

export function photoCards() {
  return [PHOTO_WORDS.cup, PHOTO_WORDS.mug, PHOTO_WORDS.bowl];
}

export function photoUsedText() {
  const used = PHOTO_TOTAL - planet.photoLeft;
  return "拍照 " + used + "/" + PHOTO_TOTAL;
}

export function speakUsedText() {
  const left = planet.speakLeft < 0 ? 0 : planet.speakLeft;
  const used = SPEAK_TOTAL - left;
  return "口评 " + used + "/" + SPEAK_TOTAL;
}

function enterLook(fromAlbum) {
  planet.photoPick = "cup";
  toast(fromAlbum ? "从相册选了一张。只认一件东西，不认人脸。" : "拍好了。芽芽开始看。");
  openView("photolook");
  clearTimeout(photoTimer);
  photoTimer = setTimeout(() => {
    if (play.view === "photolook") openView("photopick");
  }, 1400);
}

export function shootPhoto(fromAlbum) {
  if (!planet.settings.photo) {
    toast("拍照识词已经关掉。可以去闯关，或请爸爸妈妈打开。");
    return;
  }
  if (planet.photoLeft <= 0) {
    openView("photolimit");
    return;
  }
  enterLook(!!fromAlbum);
}

export function toggleFlash() {
  planet.flashOn = !planet.flashOn;
  savePlanet();
  toast(planet.flashOn ? "手电开了。暗的时候再拍。" : "手电关了。");
}

export function readyPhoto() {
  clearTimeout(photoTimer);
  openView("photopick");
}

export function cancelPhoto() {
  clearTimeout(photoTimer);
  toast("已取消。这次不占拍照次数。");
  openView("photocam");
}

export function blurPhoto() {
  clearTimeout(photoTimer);
  toast("没看清。不展示猜测，这次不占次数。");
  openView("photoblur");
}

export function pickCand(key) {
  if (!PHOTO_WORDS[key]) return;
  planet.photoPick = key;
  const word = PHOTO_WORDS[key];
  toast("换成" + word.zh + "。不用重新拍照。");
}

export function keepPhoto() {
  if (planet.photoLeft <= 0) {
    openView("photolimit");
    return;
  }
  planet.savedPhoto = planet.photoPick;
  planet.photoLeft -= 1;
  savePlanet();
  const word = savedPhotoWord();
  toast("收下 " + word.en + "。放进好吃的和单词本，不新开世界。");
  openView("photo");
}

export function playStandard() {
  const word = savedPhotoWord();
  toast("正在播放 " + word.en + "。可以再听一遍。");
}

export function followRead() {
  const word = savedPhotoWord();
  openSpeech({
    from: "photo",
    returnView: "photo",
    word: word.en,
    refText: word.en,
    title: word.en,
    zh: word.zh,
    ipa: word.ipa,
    sentence: word.line,
    subject: "english",
    evalKind: "word",
  });
}

export function answerGate(ok) {
  play.gateOn = true;
  play.gateText = ok
    ? "拍照和跟读今天的次数用完了，闯关和听还可以继续。"
    : "再请爸爸妈妈看一下，先不要把手机交给小朋友。";
}

export function toggleListen() {
  play.listenOn = !play.listenOn;
  toast(play.listenOn ? "正在播放厨房里的苹果。播完可以回星球，不自动开下一科。" : "先停在这里。");
}

export function continueToday() {
  const info = gradeInfo();
  if (planet.gradeKey === "pre") {
    openView("listen");
    toast(info.cta);
    return;
  }
  startBattle(false);
}

export function poems() {
  return [
    poemById("jing"),
    poemById("chun"),
    poemById("song"),
  ];
}

export function currentPoem() {
  return poemById(planet.poemId);
}

export function poemVisible(formKey) {
  return planet.poemForm === "all" || planet.poemForm === formKey;
}

export function openPoem(id) {
  planet.poemId = id;
  planet.poemLine = 0;
  planet.poemLayer = "yi";
  savePlanet();
  openView("poem");
}

export function setPoemForm(form) {
  planet.poemForm = form;
  savePlanet();
}

export function currentLine() {
  const poem = currentPoem();
  const line = poem.lines[planet.poemLine] || poem.lines[0];
  return line;
}

export function pickLine(index) {
  planet.poemLine = index;
  planet.poemLayer = planet.poemLayer || "yi";
  play.poetTalk = true;
  clearTimeout(poetTimer);
  poetTimer = setTimeout(() => {
    play.poetTalk = false;
  }, 650);
}

export function setLayer(layer) {
  planet.poemLayer = layer;
}

export function togglePy() {
  planet.poemPyOn = !planet.poemPyOn;
  savePlanet();
  toast(planet.poemPyOn ? "注音打开了" : "注音先藏起来");
}

export function openAuthor() {
  planet.poemLayer = "ren";
  const poem = currentPoem();
  toast(poem.author + "的介绍打开了");
}

export function readPoem(kind) {
  const poem = currentPoem();
  const line = currentLine();
  play.poetTalk = true;
  clearTimeout(poetTimer);
  poetTimer = setTimeout(() => {
    play.poetTalk = false;
  }, 700);
  let say = poem.author + "正在读「" + line.text + "」。";
  if (kind === "talk") say = "只讲这一句：" + line.yi;
  if (kind === "together") {
    openSpeech({
      from: "poem",
      returnView: "poem",
      word: line.text,
      refText: line.text,
      title: line.text,
      zh: poem.title,
      ipa: "",
      sentence: "",
      subject: "chinese",
      evalKind: "poem",
    });
    say = "一起读「" + line.text + "」。按住读，松开就结束。";
  }
  toast(say);
  return say;
}

export function activeSet() {
  return planet.math.bank === "small" ? MATH_SMALL : MATH_MAIN;
}

export function currentMathItem() {
  const math = planet.math;
  if (math.mode === "unit") return UNIT_SET[math.unitIndex % UNIT_SET.length];
  if (math.repair) return math.repair;
  const set = activeSet();
  const index = math.index >= set.length ? set.length - 1 : math.index;
  return set[index];
}

function cmpShown(item) {
  const flip = (planet.math.index + (planet.math.repair ? 1 : 0)) % 2 === 1;
  return flip ? [item.b, item.a] : [item.a, item.b];
}

export function expected(item) {
  if (planet.math.mode === "cmp" && item.a != null) {
    const pair = cmpShown(item);
    if (pair[0] > pair[1]) return ">";
    if (pair[0] < pair[1]) return "<";
    return "=";
  }
  return item.answer;
}

export function faceText(item) {
  if (planet.math.mode === "cmp" && item.a != null) {
    const pair = cmpShown(item);
    return pair[0] + " ○ " + pair[1];
  }
  return item.text;
}

export function mathFollowText() {
  const small = planet.math.bank === "small";
  if (planet.gradeKey === "pre") return "学前 · 比一比多少 · 点大图，不排名";
  if (planet.gradeKey === "g36") return small ? "四年级 · 可回看 20 以内 · 不排名" : "四年级 · 退位减法 · 做完可看正确率，不排名";
  if (planet.gradeKey === "mid") return "初一 · 仍在补小学退位减法 · 不排初中新题";
  return small ? "先回退：20 以内退位 · 按知识点，不排名" : "二年级 · 退位减法 · 按知识点，不排名";
}

export function mathView() {
  const math = planet.math;
  const item = currentMathItem();
  const pre = planet.gradeKey === "pre";
  const hideFace = !pre && math.mode === "listen" && !math.heard;
  const showVert = !pre && math.mode === "vert" && !hideFace && item.a != null;
  const showCmp = !pre && math.mode === "cmp";
  let progress = "第 " + Math.min(math.index + 1, 10) + " / 10 题 · 可停 · 不排名";
  if (math.mode === "unit") progress = "长度热身 · 不占今天这 10 题";
  if (math.index >= 10 && math.mode !== "unit") progress = "10 / 10 做完了 · 不排名";
  let hint = item.hint || "";
  if (math.awaitingWhy) hint = "还没对。先选原因，下一题数字会变。" + hint;
  if (math.gapStreak >= 2) hint += " 连续两次还不会，建议回退到 20 以内。可以先不回去。";
  let digitText = math.digits ? math.digits : "点数字作答";
  if (math.awaitingWhy) digitText = "先选原因";
  else if (showCmp) digitText = "点大于、小于或等于";
  else if (hideFace && !math.digits) digitText = "可以不看题，直接按";
  const step = math.index >= 10 ? 10 : math.index + 1;
  const bar = math.mode === "unit" ? 100 : (step / 10) * 100;
  return {
    follow: mathFollowText(),
    progress: progress,
    bar: bar,
    question: pre ? "哪一堆苹果更多？" : faceText(item),
    hideFace: hideFace,
    showVert: showVert,
    showCmp: showCmp,
    showPad: !pre && !showCmp,
    showModes: !pre,
    showWhy: !!math.awaitingWhy && !pre,
    showHeard: math.mode === "listen" && !math.heard && !pre,
    digitText: digitText,
    hint: pre ? "点更多的那一堆。不用键盘。" : hint,
    pre: pre,
    preMiss: !!math.preMiss,
    a: item.a != null ? String(item.a) : "",
    b: item.b != null ? "− " + item.b : "",
    answerShown: math.digits ? math.digits : "?",
    mode: math.mode,
  };
}

export function mathPreview() {
  const set = activeSet();
  const list = [];
  for (let i = 0; i < set.length; i += 1) {
    list.push({
      label: i + 1 + ". " + set[i].a + " − " + set[i].b,
      kind: set[i].kind,
      act: "预览第 " + (i + 1) + " 题：" + set[i].a + " − " + set[i].b + "。学生做的时候会换数字。这里不标价。",
    });
  }
  return list;
}

export function previewTitle() {
  return planet.math.bank === "small" ? "今日 10 题 · 20 以内退位 · 不绑教材" : "今日 10 题 · 退位减法 · 不绑教材";
}

export function reportText() {
  const kind = planet.math.bank === "small" ? "20 以内退位" : "退位减法";
  const math = planet.math;
  let tail = "";
  const waiting = waitingCount();
  if (waiting) tail = " 标记了 " + waiting + " 道错题，明天打开会先复习。";
  if (math.index <= 0) return "这组还没做完，现在是 0 / 10。可以先停。明天还练" + kind + "。没有名次。" + tail;
  const unstable = math.gapStreak > 0 || math.correct < math.index;
  const stable = unstable ? kind + "还不太稳。" : kind + "更稳了一点。";
  return "做了 " + math.index + " / 10 题，第一次就对了 " + math.correct + " 道。" + stable + "明天还练这个点。没有名次。" + tail;
}

export function pointOn(kind) {
  if (kind === "later") return false;
  if (planet.math.bank === "small") return kind === "fallback";
  return kind === "main";
}

export function setMathMode(mode) {
  planet.math.mode = mode;
  planet.math.heard = false;
  planet.math.digits = "";
  const says = {
    oral: "口算：一屏一题，大字。",
    vert: "竖式：同一道题，换成竖着写。",
    listen: "听算：先藏题。可以点我听到了，也可以直接答。",
    cmp: "比大小：比这一题里的两个数。没有排名。",
    unit: "厘米和米：不占今天这 10 题。",
  };
  toast(says[mode] || "已切换练法");
}

export function setMathPoint(kind, act, jump) {
  if (kind === "later") {
    toast(act || "这个点先不加新题。不会跳到后面。");
    return;
  }
  const math = planet.math;
  math.bank = kind === "fallback" ? "small" : "main";
  math.mode = "oral";
  math.index = 0;
  math.correct = 0;
  math.streak = 0;
  math.gapStreak = 0;
  math.repair = null;
  math.awaitingWhy = false;
  math.digits = "";
  math.heard = false;
  math.unitIndex = 0;
  alignLearnToPoint();
  savePlanet();
  toast(act || (math.bank === "small" ? "先回去修 20 以内退位。今天这组换成更小的数。" : "当前跟进：退位减法。"));
  if (jump) openView("math");
}

export function hearMath() {
  planet.math.heard = true;
  toast("题面出来了。也可以不看，直接按听到的答。");
}

export function submitMath(guess) {
  const math = planet.math;
  const set = activeSet();
  if (math.mode !== "unit" && !math.repair && math.index >= set.length) {
    openView("mreport");
    return;
  }
  const item = currentMathItem();
  if (String(guess) !== String(expected(item))) {
    math.awaitingWhy = true;
    math.streak = 0;
    math.lastGuess = guess ? String(guess) : "";
    math.digits = "";
    toast("还没对。先选看错了、还不会或算太快，再做一道新题。也可以标记错题，明天再练。");
    return;
  }
  math.awaitingWhy = false;
  math.digits = "";
  math.heard = false;
  if (math.mode === "unit") {
    math.unitIndex += 1;
    if (math.unitIndex >= UNIT_SET.length) {
      math.unitIndex = 0;
      math.mode = "oral";
      toast("长度热身做完了。回到今天这 10 题。");
    } else toast("做对了。再换一个长度。");
    savePlanet();
    return;
  }
  const repaired = !!math.repair;
  math.repair = null;
  if (repaired) math.streak = 0;
  else {
    math.correct += 1;
    math.streak += 1;
  }
  math.index += 1;
  if (!repaired && math.streak % 3 === 0) toast("连续做对 3 道。这个点更稳了一点。");
  else toast(repaired ? "补题做对了。回到今天这组。" : "做对了。");
  savePlanet();
  if (math.index >= activeSet().length) openView("mreport");
}

export function applyWhy(kind) {
  const math = planet.math;
  math.awaitingWhy = false;
  math.digits = "";
  math.heard = false;
  if (math.mode === "unit") {
    math.unitIndex = (math.unitIndex + 1) % UNIT_SET.length;
    toast("这道先记住：1 米 = 100 厘米。再做一道长度。");
    savePlanet();
    return;
  }
  if (kind === "gap") math.gapStreak += 1;
  else math.gapStreak = 0;
  math.repair = makeRepair(kind, math.bank, math.index, math.gapStreak);
  let extra = "";
  if (math.gapStreak >= 2) extra = "连续两次还不会。建议先回退到 20 以内。可以先不回去。";
  toast((math.repair.hint || "") + extra);
  savePlanet();
}

export function pressMathKey(key) {
  const math = planet.math;
  if (planet.gradeKey === "pre" || math.mode === "cmp") return;
  if (math.awaitingWhy) {
    toast("先选看错了、还不会或算太快。");
    return;
  }
  if (key === "ok") {
    submitMath(math.digits);
    return;
  }
  if (key === "del") {
    math.digits = math.digits.slice(0, -1);
    return;
  }
  if (/^\d$/.test(key)) math.digits = (math.digits + key).slice(-3);
}

export function submitPre(more) {
  if (more) {
    planet.math.preMiss = false;
    toast("这一堆更多。学前点大图，不用键盘。");
    return;
  }
  planet.math.preMiss = true;
  toast("这一堆少一些。想记下来，可以标记，明天再比。");
}

export function buddyMath() {
  const ans = String(expected(currentMathItem()));
  const wrong = planet.math.mode === "cmp" ? (ans === ">" ? "<" : ">") : String(Math.max(0, Number(ans) - 1));
  toast("芽芽把这题写成了 " + wrong + "。没有名次。你做对就算比过芽芽。");
}

export function photoMath() {
  const math = planet.math;
  const ans = String(expected(currentMathItem()));
  if (math.digits && math.digits === ans) {
    submitMath(math.digits);
    toast("拍到的和这题一样。只检查这张纸，不去搜题。");
    return;
  }
  const written = math.mode === "cmp" ? (ans === ">" ? "<" : ">") : String(Math.max(0, Number(ans) - 1));
  math.awaitingWhy = true;
  math.digits = "";
  math.lastGuess = written;
  toast("拍到你写的是 " + written + "。只检查这张纸，不去搜题。先选原因。也可以标记错题。");
}

export function stopMath() {
  openView("mreport");
}

export function resetMath() {
  const math = planet.math;
  math.mode = "oral";
  math.index = 0;
  math.correct = 0;
  math.streak = 0;
  math.gapStreak = 0;
  math.heard = false;
  math.repair = null;
  math.awaitingWhy = false;
  math.digits = "";
  math.unitIndex = 0;
  savePlanet();
  openView("math");
  toast("再来一组。还是这个知识点。");
}

export function finishMathDemo() {
  const math = planet.math;
  math.index = activeSet().length;
  math.correct = 8;
  math.gapStreak = 1;
  math.repair = null;
  math.awaitingWhy = false;
  math.mode = "oral";
  savePlanet();
  openView("mreport");
}

function dateKey(date) {
  const y = date.getFullYear();
  const m = date.getMonth() + 1;
  const d = date.getDate();
  const mm = m < 10 ? "0" + m : String(m);
  const dd = d < 10 ? "0" + d : String(d);
  return y + "-" + mm + "-" + dd;
}

function todayKey() {
  return dateKey(new Date());
}

function tomorrowKey() {
  const date = new Date();
  date.setDate(date.getDate() + 1);
  return dateKey(date);
}

function defaultLearn() {
  return {
    lessonId: "",
    day: "",
    step: 0,
    frame: 0,
    drill: 0,
    digits: "",
    miss: false,
    finished: false,
    lastGuess: "",
  };
}

function resetLearnProgress(learn, day) {
  learn.day = day;
  learn.step = 0;
  learn.frame = 0;
  learn.drill = 0;
  learn.digits = "";
  learn.miss = false;
  learn.finished = false;
  learn.lastGuess = "";
}

function ensureLearn() {
  const math = planet.math;
  if (!math.learn) math.learn = defaultLearn();
  if (!math.wrongs) math.wrongs = [];
  const learn = math.learn;
  const today = todayKey();
  const list = lessonsForGrade(planet.gradeKey);
  const known = lessonById(learn.lessonId);
  if (!known) {
    learn.lessonId = list[0].id;
    resetLearnProgress(learn, today);
    return true;
  }
  if (learn.finished && learn.day && learn.day !== today) {
    let nextId = list[0].id;
    for (let i = 0; i < list.length; i += 1) {
      if (list[i].id === learn.lessonId) {
        nextId = list[(i + 1) % list.length].id;
        break;
      }
    }
    learn.lessonId = nextId;
    resetLearnProgress(learn, today);
    return true;
  }
  return false;
}

function cleanChoices(list) {
  if (!list || !list.length) return null;
  const choices = [];
  for (let i = 0; i < list.length; i += 1) {
    const item = list[i];
    if (!item || item.id == null || !item.text) continue;
    choices.push({ id: String(item.id), text: String(item.text) });
  }
  return choices.length ? choices : null;
}

function cleanRedo(redo) {
  if (!redo || !redo.prompt || redo.answer == null) return null;
  return {
    prompt: String(redo.prompt),
    answer: String(redo.answer),
    say: redo.say ? String(redo.say) : "",
    choices: cleanChoices(redo.choices),
  };
}

function cleanWrongs(list) {
  if (!list || !list.length) return [];
  const next = [];
  for (let i = 0; i < list.length; i += 1) {
    const item = list[i];
    if (!item || !item.id || !item.prompt || item.answer == null) continue;
    next.push({
      id: String(item.id),
      lessonId: item.lessonId ? String(item.lessonId) : "",
      title: item.title ? String(item.title) : "错题",
      prompt: String(item.prompt),
      answer: String(item.answer),
      wrong: item.wrong ? String(item.wrong) : "",
      markedOn: item.markedOn ? String(item.markedOn) : "",
      reviewOn: item.reviewOn ? String(item.reviewOn) : "",
      status: item.status === "done" ? "done" : "wait",
      say: item.say ? String(item.say) : "",
      choices: cleanChoices(item.choices),
      redo: cleanRedo(item.redo),
    });
  }
  return next.length > 40 ? next.slice(next.length - 40) : next;
}

function dueWrongs() {
  const today = todayKey();
  const list = planet.math.wrongs || [];
  const due = [];
  for (let i = 0; i < list.length; i += 1) {
    const item = list[i];
    if (item.status !== "done" && item.reviewOn && item.reviewOn <= today) due.push(item);
  }
  return due;
}

function waitingCount() {
  const list = planet.math.wrongs || [];
  let count = 0;
  for (let i = 0; i < list.length; i += 1) {
    if (list[i].status !== "done") count += 1;
  }
  return count;
}

function promptSeed(text) {
  let seed = 0;
  const value = text || "";
  for (let i = 0; i < value.length; i += 1) seed += value.charCodeAt(i);
  return seed;
}

function ensureRedo(wrong) {
  if (wrong.redo && wrong.redo.prompt) return;
  const seed = promptSeed(wrong.prompt);
  if (wrong.answer === ">" || wrong.answer === "<" || wrong.answer === "=") {
    wrong.redo = redoCompare(seed, wrong.prompt);
    return;
  }
  if (wrong.lessonId === "unit-length") {
    wrong.redo = redoUnit(seed, wrong.prompt);
    return;
  }
  const lesson = lessonById(wrong.lessonId) || lessonsForGrade(planet.gradeKey)[0];
  wrong.redo = redoItem(lesson, seed, wrong.prompt);
}

function prepareMathDesk() {
  const changed = ensureLearn();
  const due = dueWrongs();
  const today = todayKey();
  if (due.length && planet.math.reviewDay !== today && planet.math.desk !== "review") {
    planet.math.desk = "review";
    planet.math.reviewDigits = "";
    planet.math.reviewMiss = false;
    ensureRedo(due[0]);
    savePlanet();
    return;
  }
  if (planet.math.desk === "review") {
    if (!due.length) {
      planet.math.desk = planet.math.learn && planet.math.learn.finished ? "oral" : "learn";
      savePlanet();
      return;
    }
    ensureRedo(due[0]);
    if (changed) savePlanet();
    return;
  }
  if (changed) savePlanet();
}
function alignLearnToPoint() {
  if (planet.gradeKey === "pre") return;
  if (!planet.math.learn) planet.math.learn = defaultLearn();
  const learn = planet.math.learn;
  const nextId = planet.math.bank === "small" ? "borrow-twenty" : "borrow-tens";
  if (learn.lessonId === nextId) return;
  learn.lessonId = nextId;
  resetLearnProgress(learn, todayKey());
}

function currentLearnLesson() {
  const learn = planet.math.learn || defaultLearn();
  return lessonById(learn.lessonId) || lessonsForGrade(planet.gradeKey)[0];
}

function currentLearnItem(lesson, learn) {
  if (learn.step >= 4) {
    const index = learn.drill >= lesson.drills.length ? lesson.drills.length - 1 : learn.drill;
    if (index < 0) return lesson.model;
    return lesson.drills[index];
  }
  return lesson.model;
}

function pushWrong(info) {
  if (!planet.math.wrongs) planet.math.wrongs = [];
  const today = todayKey();
  const tomorrow = tomorrowKey();
  const wrongs = planet.math.wrongs;
  for (let i = 0; i < wrongs.length; i += 1) {
    const item = wrongs[i];
    if (item.prompt === info.prompt && item.status !== "done") {
      item.wrong = info.wrong || item.wrong;
      item.markedOn = today;
      item.reviewOn = tomorrow;
      item.redo = null;
      item.say = info.say || item.say;
      savePlanet();
      toast("这道已经在错题里。明天会再练一道同型题。");
      return;
    }
  }
  wrongs.push({
    id: "mw" + Date.now() + "-" + wrongs.length,
    lessonId: info.lessonId || "",
    title: info.title || "错题",
    prompt: info.prompt,
    answer: String(info.answer),
    wrong: info.wrong || "",
    markedOn: today,
    reviewOn: tomorrow,
    status: "wait",
    say: info.say || "",
    choices: info.choices || null,
    redo: null,
  });
  if (wrongs.length > 40) planet.math.wrongs = wrongs.slice(wrongs.length - 40);
  savePlanet();
  toast("已标记。明天打开数学，会先复习同型题。");
}

function learnFollow() {
  if (planet.gradeKey === "pre") return "学前 · 先看，再读一句口诀，再点一题";
  if (planet.gradeKey === "g36") return "小学高年级 · 讲解、动画、口诀、母题";
  if (planet.gradeKey === "mid") return "初中 · 补小学里没稳的点，不排新的初中题";
  return "小学低年级 · 讲解、动画、口诀、母题";
}

export function teachView() {
  const math = planet.math;
  const learn = math.learn || defaultLearn();
  const lesson = currentLearnLesson();
  const due = dueWrongs();
  const reviewing = due.length ? due[0] : null;
  const redo = reviewing && reviewing.redo ? reviewing.redo : null;
  const step = learn.step < 0 ? 0 : learn.step;
  const stepIndex = step >= 3 ? 3 : step;
  const item = currentLearnItem(lesson, learn);
  const frameIndex = learn.frame >= lesson.frames.length ? lesson.frames.length - 1 : learn.frame;
  const frame = lesson.frames[frameIndex] || lesson.frames[0];
  let nextLabel = "做母题";
  if (stepIndex === 0) nextLabel = "看动画";
  else if (stepIndex === 1 && frameIndex < lesson.frames.length - 1) nextLabel = "再看一步";
  else if (stepIndex === 1) nextLabel = "读口诀";
  let progress = "先做这道母题";
  if (step >= 4) progress = "同型题 " + (learn.drill + 1) + " / " + lesson.drills.length;
  let hint = "看完再往下。";
  if (stepIndex === 2) hint = "跟着读一遍就行。";
  if (step >= 3 && learn.miss) hint = item.say;
  else if (step === 3) hint = "这是今天的母题。";
  else if (step >= 4) hint = "数字换了，方法一样。";
  const finishedToday = !!(learn.finished && learn.day === todayKey());
  const reviewChoices = redo && redo.choices ? redo.choices : null;
  let reviewHint = "方法一样，数字换过了。";
  if (math.reviewMiss && redo) reviewHint = redo.say;
  return {
    desk: math.desk || "oral",
    follow: learnFollow(),
    title: lesson.title,
    stage: lesson.stage,
    explain: lesson.explain,
    chant: lesson.chant,
    frames: lesson.frames,
    frame: frameIndex < 0 ? 0 : frameIndex,
    frameTitle: frame.title,
    frameText: frame.text,
    frameDots: frame.dots,
    stepNames: ["看一看", "看动画", "读口诀", "做母题"],
    stepIndex: stepIndex,
    nextLabel: nextLabel,
    progress: progress,
    prompt: item.prompt,
    choices: !learn.finished && step >= 3 && item.choices ? item.choices : null,
    showPad: !learn.finished && step >= 3 && !item.choices && planet.gradeKey !== "pre",
    digitText: learn.digits ? learn.digits : "点数字作答",
    hint: hint,
    miss: !!learn.miss,
    finished: !!learn.finished,
    entryTitle: "先学再练 · " + lesson.title,
    entryNote: finishedToday ? "今天这一点学完了，还可以再看一遍" : "看讲解、看动画、读口诀、做母题",
    dueCount: due.length,
    waitCount: waitingCount(),
    wasPrompt: reviewing ? reviewing.prompt : "",
    wasWrong: reviewing && reviewing.wrong ? reviewing.wrong : "还没写对",
    reviewTitle: reviewing ? reviewing.title : "",
    reviewPrompt: redo ? redo.prompt : "",
    reviewChoices: reviewChoices,
    reviewPad: !!redo && !reviewChoices && planet.gradeKey !== "pre",
    reviewDigits: math.reviewDigits ? math.reviewDigits : "点数字作答",
    reviewHint: reviewHint,
    reviewMiss: !!math.reviewMiss,
  };
}

export function openLearn() {
  ensureLearn();
  planet.math.desk = "learn";
  savePlanet();
  toast("先看一看，再读口诀，最后做母题。");
}

export function backToOral() {
  if (planet.math.desk === "review") planet.math.reviewDay = todayKey();
  planet.math.desk = "oral";
  savePlanet();
  toast("回到口算。");
}

export function openReview() {
  const due = dueWrongs();
  if (!due.length) {
    toast("还没有到复习日的错题。");
    return;
  }
  planet.math.desk = "review";
  planet.math.reviewDigits = "";
  planet.math.reviewMiss = false;
  ensureRedo(due[0]);
  savePlanet();
}

export function replayLearn() {
  const learn = planet.math.learn;
  resetLearnProgress(learn, todayKey());
  learn.lessonId = currentLearnLesson().id;
  planet.math.desk = "learn";
  savePlanet();
  toast("再看一遍这个知识点。");
}

export function nextLearnStep() {
  const learn = planet.math.learn;
  if (!learn || learn.finished) return;
  const lesson = currentLearnLesson();
  if (learn.step === 1 && learn.frame < lesson.frames.length - 1) {
    learn.frame += 1;
    savePlanet();
    return;
  }
  if (learn.step < 3) {
    learn.step += 1;
    learn.miss = false;
    learn.digits = "";
    if (learn.step === 1) learn.frame = 0;
    savePlanet();
  }
}

export function submitLearn(guess) {
  const learn = planet.math.learn;
  if (!learn || learn.finished || learn.step < 3) return;
  const lesson = currentLearnLesson();
  const item = currentLearnItem(lesson, learn);
  if (!item) return;
  if (String(guess) !== String(item.answer)) {
    learn.miss = true;
    learn.lastGuess = guess ? String(guess) : "";
    learn.digits = "";
    toast("还没对。可以再试，也可以标记成错题，明天再练。");
    return;
  }
  learn.miss = false;
  learn.digits = "";
  learn.lastGuess = "";
  if (learn.step === 3) {
    learn.step = 4;
    learn.drill = 0;
    toast("母题做对了。再做几道只改数字的。");
    savePlanet();
    return;
  }
  if (learn.drill < lesson.drills.length - 1) {
    learn.drill += 1;
    toast("做对了。数字换了，方法一样。");
    savePlanet();
    return;
  }
  learn.finished = true;
  learn.day = todayKey();
  toast("今天这一点学完了。可以去口算。");
  savePlanet();
}

export function pressLearnKey(key) {
  const learn = planet.math.learn;
  if (!learn || learn.finished || learn.step < 3) return;
  const item = currentLearnItem(currentLearnLesson(), learn);
  if (!item || item.choices || planet.gradeKey === "pre") return;
  if (key === "ok") {
    if (!learn.digits) {
      toast("先点数字。");
      return;
    }
    submitLearn(learn.digits);
    return;
  }
  if (key === "del") {
    learn.digits = learn.digits.slice(0, -1);
    return;
  }
  if (/^\d$/.test(key)) learn.digits = (learn.digits + key).slice(-3);
}

export function markLearnMiss() {
  const learn = planet.math.learn;
  if (!learn || !learn.miss) {
    toast("先做一次。没做对再标记。");
    return;
  }
  const lesson = currentLearnLesson();
  const item = currentLearnItem(lesson, learn);
  pushWrong({
    lessonId: lesson.id,
    title: lesson.title,
    prompt: item.prompt,
    answer: item.answer,
    wrong: learn.lastGuess || "还没做对",
    say: item.say,
    choices: item.choices,
  });
}

export function markOralWrong() {
  const math = planet.math;
  if (!math.awaitingWhy) {
    toast("这道还没做错。做错过再标记。");
    return;
  }
  const item = currentMathItem();
  let lessonId = math.bank === "small" ? "borrow-twenty" : "borrow-tens";
  let title = item.kind || "口算";
  let choices = null;
  if (math.mode === "unit") {
    lessonId = "unit-length";
    title = "厘米和米";
  }
  if (math.mode === "cmp") {
    lessonId = "more-less";
    title = "比大小";
    choices = [
      { id: ">", text: "大于" },
      { id: "<", text: "小于" },
      { id: "=", text: "等于" },
    ];
  }
  pushWrong({
    lessonId: lessonId,
    title: title,
    prompt: faceText(item),
    answer: String(expected(item)),
    wrong: math.lastGuess || "还没写对",
    say: item.hint || "",
    choices: choices,
  });
}

export function markPreWrong() {
  if (!planet.math.preMiss) {
    toast("先点一次。点错了再标记。");
    return;
  }
  pushWrong({
    lessonId: "more-less",
    title: "比多少",
    prompt: "哪一堆苹果更多？",
    answer: "right",
    wrong: "点了少的那一堆",
    say: "更多的那一堆再数一次。",
    choices: [
      { id: "left", text: "左边少一些" },
      { id: "right", text: "右边更多" },
    ],
  });
}

export function submitReview(guess) {
  const due = dueWrongs();
  const wrong = due.length ? due[0] : null;
  if (!wrong || !wrong.redo) return;
  if (String(guess) !== String(wrong.redo.answer)) {
    planet.math.reviewMiss = true;
    planet.math.reviewDigits = "";
    toast("还没对。可以再试，也可以留到明天。");
    return;
  }
  wrong.status = "done";
  planet.math.reviewMiss = false;
  planet.math.reviewDigits = "";
  const left = dueWrongs();
  if (left.length) {
    ensureRedo(left[0]);
    toast("这道移出复习了。还有一道昨天的错题。");
  } else {
    planet.math.reviewDay = todayKey();
    planet.math.desk = planet.math.learn && planet.math.learn.finished ? "oral" : "learn";
    toast("昨天的错题都复习过了。");
  }
  savePlanet();
}

export function pressReviewKey(key) {
  const due = dueWrongs();
  const wrong = due.length ? due[0] : null;
  if (!wrong || !wrong.redo || wrong.redo.choices || planet.gradeKey === "pre") return;
  const math = planet.math;
  if (key === "ok") {
    if (!math.reviewDigits) {
      toast("先点数字。");
      return;
    }
    submitReview(math.reviewDigits);
    return;
  }
  if (key === "del") {
    math.reviewDigits = math.reviewDigits.slice(0, -1);
    return;
  }
  if (/^\d$/.test(key)) math.reviewDigits = (math.reviewDigits + key).slice(-3);
}

export function holdReview() {
  const due = dueWrongs();
  const wrong = due.length ? due[0] : null;
  if (!wrong) return;
  wrong.reviewOn = tomorrowKey();
  wrong.redo = null;
  planet.math.reviewMiss = false;
  planet.math.reviewDigits = "";
  const left = dueWrongs();
  if (left.length) {
    ensureRedo(left[0]);
    toast("这道留到明天。先看下一道到期的。");
  } else {
    planet.math.reviewDay = todayKey();
    planet.math.desk = "oral";
    toast("这道留到明天再复习。");
  }
  savePlanet();
}

export function skipReviewToLearn() {
  planet.math.reviewDay = todayKey();
  ensureLearn();
  planet.math.desk = "learn";
  savePlanet();
  toast("先学今天的知识点。错题还留着，可以再回来。");
}

export function cycleGrade() {
  const index = GRADE_ORDER.indexOf(planet.gradeKey);
  const next = GRADE_ORDER[(index + 1) % GRADE_ORDER.length];
  planet.gradeKey = next || "g12";
  const list = lessonsForGrade(planet.gradeKey);
  const learn = planet.math.learn || defaultLearn();
  planet.math.learn = learn;
  let allowed = false;
  for (let i = 0; i < list.length; i += 1) {
    if (list[i].id === learn.lessonId) allowed = true;
  }
  if (!allowed) {
    learn.lessonId = list[0].id;
    resetLearnProgress(learn, todayKey());
  }
  planet.math.desk = "oral";
  savePlanet();
  toast(gradeInfo().toast);
}

export function toggleSetting(key) {
  if (!Object.prototype.hasOwnProperty.call(planet.settings, key)) return;
  planet.settings[key] = !planet.settings[key];
  savePlanet();
  toast(planet.settings[key] ? "已为豆豆打开这项" : "已为豆豆关闭这项");
}

export function memberClosed() {
  toast("先不能支付。孩子端看不到价格，闯关和听还可以继续。");
}

export function gotWords() {
  return play.got.length ? play.got : ["apple", "cup", "plate"];
}

export function missedWords() {
  if (play.missed.length) return play.missed;
  if (play.got.length) return [];
  return ["fork"];
}

let speechTimer = 0;
let speechHeld = false;
let speechReleaseBound = false;
let ignoreMouseUntil = 0;

function todayKey() {
  const now = new Date();
  const month = now.getMonth() + 1;
  const day = now.getDate();
  const monthText = month < 10 ? "0" + month : String(month);
  const dayText = day < 10 ? "0" + day : String(day);
  return now.getFullYear() + "-" + monthText + "-" + dayText;
}

function refreshSpeakQuota() {
  const today = todayKey();
  if (planet.speakDay === today) return;
  planet.speakDay = today;
  planet.speakLeft = SPEAK_TOTAL;
  savePlanet();
}

function reviewKey(subject, refText) {
  return subject + ":" + refText;
}

function cleanReviews(list) {
  const out = [];
  if (!list || !list.length) return out;
  for (let i = 0; i < list.length; i += 1) {
    const item = list[i];
    if (!item || !item.id || !item.refText || !item.title) continue;
    out.push({
      id: String(item.id),
      title: String(item.title),
      zh: item.zh ? String(item.zh) : "",
      refText: String(item.refText),
      subject: item.subject === "chinese" ? "chinese" : "english",
      evalKind: item.evalKind ? String(item.evalKind) : "word",
      ipa: item.ipa ? String(item.ipa) : "",
      sentence: item.sentence ? String(item.sentence) : "",
      word: item.word ? String(item.word) : String(item.refText),
    });
    if (out.length >= 30) break;
  }
  return out;
}

function findReview(id) {
  const reviews = planet.speechReviews || [];
  for (let i = 0; i < reviews.length; i += 1) {
    if (reviews[i].id === id) return reviews[i];
  }
  return null;
}

function makeSpeech(target) {
  const refText = target.refText;
  const id = reviewKey(target.subject, refText);
  return {
    from: target.from,
    returnView: target.returnView,
    word: target.word,
    refText: refText,
    title: target.title,
    zh: target.zh || "",
    ipa: target.ipa || "",
    sentence: target.sentence || "",
    subject: target.subject,
    evalKind: target.evalKind,
    piece: target.evalKind === "sentence" ? "sentence" : "word",
    phase: "ready",
    seconds: 0,
    status: "",
    stars: 0,
    label: "",
    hint: "",
    canReview: false,
    inReview: !!findReview(id),
    reviewId: id,
  };
}

function syncReviewFlag(speech) {
  speech.reviewId = reviewKey(speech.subject, speech.refText);
  speech.inReview = !!findReview(speech.reviewId);
}

export function openSpeech(target) {
  resetSpeechHold();
  refreshSpeakQuota();
  clearSpeechTimer();
  discardRecording();
  play.speech = makeSpeech(target);
  if (!planet.settings.speak) {
    play.speechAsk = true;
    play.speechOn = false;
    return;
  }
  play.speechAsk = false;
  play.speech.phase = planet.speakLeft <= 0 ? "limit" : "ready";
  play.speechOn = true;
}

export function allowSpeak() {
  planet.settings.speak = true;
  savePlanet();
  play.speechAsk = false;
  if (!play.speech) return;
  refreshSpeakQuota();
  play.speech.phase = planet.speakLeft <= 0 ? "limit" : "ready";
  play.speechOn = true;
  toast("已同意。按住读，松开就结束。不显示分数。");
}

export function denySpeak() {
  resetSpeechHold();
  const speech = play.speech;
  play.speechAsk = false;
  play.speechOn = false;
  play.speech = null;
  if (speech && speech.subject === "english") toast("先不上传。可以听 " + speech.refText + "。");
  else toast("先不上传。这次不打星。");
}

export function closeSpeech() {
  resetSpeechHold();
  clearSpeechTimer();
  discardRecording();
  const back = play.speech ? play.speech.returnView : "";
  play.speechOn = false;
  play.speechAsk = false;
  play.speech = null;
  if (back) openView(back);
}

export function setSpeechPiece(kind) {
  const speech = play.speech;
  if (!speech) return;
  if (speech.phase === "recording" || speech.phase === "opening" || speech.phase === "assessing") return;
  if (kind === "sentence" && speech.sentence) {
    speech.refText = speech.sentence;
    speech.evalKind = "sentence";
    speech.piece = "sentence";
  } else {
    speech.refText = speech.word;
    speech.evalKind = speech.subject === "chinese" ? "poem" : "word";
    speech.piece = "word";
  }
  speech.phase = "ready";
  speech.status = "";
  speech.stars = 0;
  speech.label = "";
  speech.hint = "";
  speech.canReview = false;
  speech.seconds = 0;
  syncReviewFlag(speech);
}

export function playSpeechSample() {
  const speech = play.speech;
  if (!speech) return;
  if (speech.subject === "chinese") {
    toast("再听这一句：" + speech.refText);
    return;
  }
  toast("正在播放 " + speech.refText + "。");
}

function bindSpeechRelease() {
  if (speechReleaseBound || typeof document === "undefined") return;
  speechReleaseBound = true;
  document.addEventListener("mouseup", releaseSpeech, true);
  document.addEventListener("touchend", releaseSpeech, true);
  document.addEventListener("touchcancel", releaseSpeech, true);
}

function unbindSpeechRelease() {
  if (!speechReleaseBound || typeof document === "undefined") {
    speechReleaseBound = false;
    return;
  }
  speechReleaseBound = false;
  document.removeEventListener("mouseup", releaseSpeech, true);
  document.removeEventListener("touchend", releaseSpeech, true);
  document.removeEventListener("touchcancel", releaseSpeech, true);
}

function resetSpeechHold() {
  speechHeld = false;
  unbindSpeechRelease();
}

export function pressSpeech(event) {
  const fromMouse = event && event.type === "mousedown";
  if (fromMouse && event.button !== undefined && event.button !== 0) return;
  if (fromMouse && Date.now() < ignoreMouseUntil) return;
  if (speechHeld) return;
  const speech = play.speech;
  if (!speech) return;
  if (speech.phase === "opening" || speech.phase === "recording" || speech.phase === "assessing" || speech.phase === "limit") return;
  if (event && event.type === "touchstart") ignoreMouseUntil = Date.now() + 800;
  speechHeld = true;
  speech.releaseEarly = false;
  bindSpeechRelease();
  if (!startSpeech()) resetSpeechHold();
}

export function releaseSpeech() {
  if (!speechHeld) return;
  speechHeld = false;
  unbindSpeechRelease();
  const speech = play.speech;
  if (!speech) return;
  if (speech.phase === "recording") {
    stopSpeech();
    return;
  }
  if (speech.phase === "opening") speech.releaseEarly = true;
}

export function startSpeech() {
  const speech = play.speech;
  if (!speech) return false;
  if (speech.phase === "recording" || speech.phase === "opening" || speech.phase === "assessing") return false;
  refreshSpeakQuota();
  if (planet.speakLeft <= 0) {
    if (speech.phase === "result" && speech.stars === 0 && !speech.inReview) {
      toast("今天次数用完了。这句可以先放进复习。");
      return false;
    }
    speech.phase = "limit";
    return false;
  }
  speech.phase = "opening";
  speech.seconds = 0;
  speech.status = "";
  speech.hint = "";
  beginRecording().then((started) => {
    if (!play.speech || play.speech !== speech) {
      discardRecording();
      return;
    }
    if (!started || speech.phase !== "opening") {
      discardRecording();
      if (play.speech === speech && (speech.releaseEarly || speech.phase === "opening")) {
        speech.phase = "result";
        speech.status = "unclear";
        speech.hint = "没听清。按住橙色按钮读，松开再结束。这次不扣次数。";
        speech.canReview = false;
      }
      return;
    }
    speech.phase = "recording";
    speech.startedAt = Date.now();
    clearSpeechTimer();
    speechTimer = setInterval(() => {
      if (!play.speech || play.speech.phase !== "recording") return;
      const seconds = Math.floor((Date.now() - speech.startedAt) / 1000);
      speech.seconds = seconds;
      if (seconds >= 8) stopSpeech();
    }, 200);
    if (speech.releaseEarly || !speechHeld) stopSpeech();
  }).catch(() => {
    if (!play.speech || play.speech !== speech) return;
    speech.phase = "result";
    speech.status = "failed";
    speech.hint = "没有打开麦克风。可以先听标准音，这次不扣次数。";
    speech.canReview = false;
  });
  return true;
}

export function stopSpeech() {
  const speech = play.speech;
  if (!speech || speech.phase !== "recording") return;
  clearSpeechTimer();
  speech.phase = "assessing";
  finishRecording().then((result) => {
    if (!play.speech || play.speech !== speech) return;
    if (!result || !result.ok) {
      speech.phase = "result";
      speech.status = result && result.reason === "short" ? "unclear" : "failed";
      speech.hint = speech.status === "unclear" ? "没听清。再读一次，这次不扣次数。" : "这段录音没准备好。先听标准音，不扣次数。";
      speech.canReview = false;
      return;
    }
    return assessSpeech({
      refText: speech.refText,
      audioBase64: result.audioBase64,
      audioFormat: result.format,
      subject: speech.subject,
      evalKind: speech.evalKind,
      gradeKey: planet.gradeKey,
    }).then((res) => {
      if (!play.speech || play.speech !== speech) return;
      applySpeechResult(speech, res && res.data ? res.data : null);
    });
  }).catch(() => {
    if (!play.speech || play.speech !== speech) return;
    speech.phase = "result";
    speech.status = "failed";
    speech.hint = "这次没连上。先听标准音，不扣次数。";
    speech.canReview = false;
  });
}

function applySpeechResult(speech, data) {
  speech.phase = "result";
  speech.canReview = false;
  if (!data || data.status !== "scored") {
    speech.status = data && data.status ? data.status : "failed";
    speech.stars = 0;
    speech.label = "";
    speech.hint = data && data.hint ? data.hint : "这次没评成。先听标准音，不扣次数。";
    return;
  }
  const stars = typeof data.stars === "number" ? data.stars : 0;
  speech.status = "scored";
  speech.stars = stars;
  speech.label = data.label || "";
  speech.hint = data.hint || "";
  speech.canReview = stars === 0;
  if (data.countQuota) {
    planet.speakLeft = planet.speakLeft > 0 ? planet.speakLeft - 1 : 0;
  }
  planet.lastSpeech = {
    title: speech.title,
    label: speech.label,
    hint: speech.hint,
  };
  syncReviewFlag(speech);
  savePlanet();
}

export function keepSpeechReview() {
  const speech = play.speech;
  if (!speech || speech.status !== "scored" || speech.stars !== 0) return;
  syncReviewFlag(speech);
  if (speech.inReview) {
    toast("已经在复习里了");
    return;
  }
  const reviews = planet.speechReviews || [];
  reviews.push({
    id: speech.reviewId,
    title: speech.title,
    zh: speech.zh,
    refText: speech.refText,
    subject: speech.subject,
    evalKind: speech.evalKind,
    ipa: speech.ipa,
    sentence: speech.sentence,
    word: speech.word,
  });
  planet.speechReviews = reviews.length > 30 ? reviews.slice(reviews.length - 30) : reviews;
  speech.inReview = true;
  savePlanet();
  toast("放进复习了。单词本的困难词里可以再读。");
}

export function dropReview(id) {
  const reviews = planet.speechReviews || [];
  const next = [];
  for (let i = 0; i < reviews.length; i += 1) {
    if (reviews[i].id !== id) next.push(reviews[i]);
  }
  planet.speechReviews = next;
  if (play.speech && play.speech.reviewId === id) play.speech.inReview = false;
  savePlanet();
  toast("已移出复习");
}

export function againReview(id) {
  const item = findReview(id);
  if (!item) return;
  openSpeech({
    from: "book",
    returnView: "book",
    word: item.word || item.refText,
    refText: item.refText,
    title: item.title,
    zh: item.zh,
    ipa: item.ipa,
    sentence: item.sentence,
    subject: item.subject,
    evalKind: item.evalKind,
  });
}

function clearSpeechTimer() {
  if (!speechTimer) return;
  clearInterval(speechTimer);
  speechTimer = 0;
}
