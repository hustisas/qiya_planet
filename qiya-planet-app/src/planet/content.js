export const PHOTO_TOTAL = 3;
export const SPEAK_TOTAL = 5;

export const WORLDS = [
  { id: "home", emoji: "🏠", name: "我家", state: "done", note: "3 星通关", act: "我家已经点亮。可以再复习，进度不会被清空。" },
  { id: "food", emoji: "🍎", name: "好吃的", state: "current", note: "第 3 / 6 关" },
  { id: "animal", emoji: "🐱", name: "小动物", state: "lock", note: "未解锁", act: "小动物还没解锁。先把好吃的闯完，这里不能付费提前打开。" },
  { id: "color", emoji: "🎨", name: "彩虹颜色", state: "lock", note: "未解锁", act: "彩虹颜色还没解锁。基础世界要一个一个点亮。" },
  { id: "school", emoji: "🏫", name: "去学校", state: "lock", note: "未解锁", act: "去学校还没解锁。先完成好吃的。" },
];

export const PHOTO_WORDS = {
  cup: { en: "cup", zh: "杯子", emoji: "☕", ipa: "/kʌp/", line: "I see a cup on the table." },
  mug: { en: "mug", zh: "茶杯", emoji: "🍵", ipa: "/mʌg/", line: "This mug is warm." },
  bowl: { en: "bowl", zh: "碗", emoji: "🥣", ipa: "/boʊl/", line: "The bowl is on the table." },
};

export const BATTLE_QUESTIONS = [
  {
    focus: "apple",
    lead: "先在心里说出来，再选",
    q: "听一听，哪张图是 apple？",
    sound: "正在播放 apple。先在心里说出来，再选。",
    shell: "记忆贝壳：apple 是红苹果砸在桌上，叮！",
    repair: "先放进修理袋。这一关结束前，还会用听音再练一次。",
    choices: [
      { text: "🍎 苹果", ok: true },
      { text: "🍌 香蕉", ok: false },
      { text: "🥛 牛奶", ok: false },
      { text: "🍞 面包", ok: false },
    ],
  },
  {
    focus: "cup",
    lead: "先在心里说出来，再选",
    q: "cup 是哪一个？",
    sound: "正在播放 cup。",
    shell: "记忆贝壳：cup 是杯子，口朝上。",
    repair: "先放进修理袋。这一关结束前还会再出现。",
    choices: [
      { text: "☕ 杯子", ok: true },
      { text: "🥣 碗", ok: false },
      { text: "🍴 叉子", ok: false },
      { text: "🍽️ 盘子", ok: false },
    ],
  },
  {
    focus: "fork",
    lead: "修理袋里的词，再听一次",
    q: "听一听，哪张图是 fork？",
    sound: "正在播放 fork。",
    shell: "记忆贝壳：fork 是叉子，一头分叉。",
    repair: "fork 先放进修理袋。明天还会遇见它。",
    choices: [
      { text: "🍴 叉子", ok: true },
      { text: "🥄 勺子", ok: false },
      { text: "🔪 刀", ok: false },
      { text: "🍎 苹果", ok: false },
    ],
  },
  {
    focus: "plate",
    lead: "先在心里说出来，再选",
    q: "plate 是哪一个？",
    sound: "正在播放 plate。",
    shell: "记忆贝壳：plate 是平平的盘子。",
    repair: "先放进修理袋。",
    choices: [
      { text: "🍽️ 盘子", ok: true },
      { text: "🥣 碗", ok: false },
      { text: "☕ 杯子", ok: false },
      { text: "🥛 牛奶", ok: false },
    ],
  },
];

export const SPELL_WORDS = [
  { en: "apple", zh: "苹果", emoji: "🍎" },
  { en: "cup", zh: "杯子", emoji: "☕" },
  { en: "fork", zh: "叉子", emoji: "🍴" },
  { en: "plate", zh: "盘子", emoji: "🍽️" },
  { en: "cat", zh: "猫", emoji: "🐱" },
  { en: "book", zh: "书", emoji: "📖" },
  { en: "lamp", zh: "台灯", emoji: "💡" },
];

export const POEMS = [
  {
    id: "jing",
    title: "静夜思",
    dynasty: "唐代",
    author: "李白",
    face: "李",
    form: "五言绝句",
    formKey: "wu",
    tag: "课内",
    yi: "明亮的月光照在眼前，好像地上铺了一层霜。抬起头看见月亮，低下头想起家乡。",
    bei: "李白这时不在家里。夜里看见月亮，就想起了远方的家乡。",
    shang: "先看见月光，再抬头，再低头想家。动作很小，想念很大。",
    ren: "李白，唐代诗人。小朋友可以先记住：他常常写月亮，也常常写出门在外。",
    lines: [
      { text: "床前明月光，", py: "chuáng qián míng yuè guāng", yi: "眼前这一片，洒满了月光。", shang: "先看见光，人还没有抬头。", notes: [["床", "先记成眼前这一片。好几种说法先不一起讲。"], ["明月", "夜里的月亮。"]] },
      { text: "疑是地上霜。", py: "yí shì dì shàng shuāng", yi: "好像地上铺了一层霜。", shang: "月光很白，才让人看成霜。", notes: [["疑", "好像。"], ["霜", "白色的霜，不是真的结冰。"]] },
      { text: "举头望明月，", py: "jǔ tóu wàng míng yuè", yi: "抬起头，看见天上的月亮。", shang: "到这里，人才抬起头。", notes: [["举头", "抬起头。"], ["望", "往远处看。"]] },
      { text: "低头思故乡。", py: "dī tóu sī gù xiāng", yi: "低下头，想起了家乡。", shang: "头低下去，心里回到了家。", notes: [["低头", "低下头。"], ["故乡", "家乡。"]] },
    ],
  },
  {
    id: "chun",
    title: "春晓",
    dynasty: "唐代",
    author: "孟浩然",
    face: "孟",
    form: "五言绝句",
    formKey: "wu",
    tag: "课内",
    yi: "春天睡觉不知不觉天就亮了，到处都是鸟叫。想起夜里的风声雨声，不知道花落了多少。",
    bei: "这是一首写春天早晨的短诗。人还没出门，先听见鸟，再想起夜里的风雨。",
    shang: "人在被窝里，春天已经发生了。",
    ren: "孟浩然，唐代诗人。小朋友可以先记住：他写的春天很安静。",
    lines: [
      { text: "春眠不觉晓，", py: "chūn mián bù jué xiǎo", yi: "春天睡觉，不知不觉天就亮了。", shang: "觉是睡醒。天已经亮了，人还在睡。", notes: [["眠", "睡觉。"], ["晓", "天亮。"]] },
      { text: "处处闻啼鸟。", py: "chù chù wén tí niǎo", yi: "到处都能听见鸟叫。", shang: "还没睁眼，春天先从耳朵进来。", notes: [["处处", "到处。"], ["啼", "鸟叫。"]] },
      { text: "夜来风雨声，", py: "yè lái fēng yǔ shēng", yi: "夜里听见过刮风下雨的声音。", shang: "这一句往回想，想到夜里。", notes: [["夜来", "夜里。"]] },
      { text: "花落知多少。", py: "huā luò zhī duō shǎo", yi: "不知道花落了多少。", shang: "他没有去数，只是在想。", notes: [["知多少", "不知道有多少。"]] },
    ],
  },
  {
    id: "song",
    title: "赠汪伦",
    dynasty: "唐代",
    author: "李白",
    face: "李",
    form: "七言绝句",
    formKey: "qi",
    tag: "扩展",
    yi: "李白坐船正要离开，忽然听见岸上有人一边走一边唱歌。桃花潭的水就算有千尺深，也比不上汪伦送我的情意。",
    bei: "汪伦来送李白。这首诗把很深的水和很深的情意放在一起。",
    shang: "前面是离别的声音，后面才说出谁在送。",
    ren: "还是李白。这一首写朋友来送他。",
    lines: [
      { text: "李白乘舟将欲行，", py: "lǐ bái chéng zhōu jiāng yù xíng", yi: "李白坐上小船，正要出发。", shang: "人已经在船上，还没走远。", notes: [["乘舟", "坐船。"], ["将欲行", "正要走。"]] },
      { text: "忽闻岸上踏歌声。", py: "hū wén àn shàng tà gē shēng", yi: "忽然听见岸上有脚步，还有歌声。", shang: "先听见，还没看见是谁。", notes: [["忽闻", "忽然听见。"], ["踏歌", "一边走，一边唱。"]] },
      { text: "桃花潭水深千尺，", py: "táo huā tán shuǐ shēn qiān chǐ", yi: "桃花潭的水，深得好像有千尺。", shang: "用水的深，好去比下一句的情意。", notes: [["潭", "比较深的水。"], ["千尺", "形容非常深，不是让人去量。"]] },
      { text: "不及汪伦送我情。", py: "bù jí wāng lún sòng wǒ qíng", yi: "还是比不上汪伦送我的情意。", shang: "到这一句，才说出送行的人是汪伦。", notes: [["不及", "比不上。"], ["汪伦", "来送李白的朋友。"]] },
    ],
  },
];

export function poemById(id) {
  for (let i = 0; i < POEMS.length; i += 1) {
    if (POEMS[i].id === id) return POEMS[i];
  }
  return POEMS[0];
}

function subItem(a, b, kind, hint) {
  return { a: a, b: b, text: a + " − " + b + " = ?", answer: String(a - b), kind: kind, hint: hint };
}

export const MATH_MAIN = [[32, 17], [41, 16], [53, 28], [70, 26], [64, 37], [85, 49], [92, 58], [76, 18], [50, 23], [81, 45]].map(function (pair) {
  return subItem(pair[0], pair[1], "退位减法", "个位不够减，向十位借 1。");
});

export const MATH_SMALL = [[12, 5], [13, 6], [14, 8], [15, 7], [11, 3], [16, 9], [12, 7], [13, 5], [14, 6], [15, 8]].map(function (pair) {
  return subItem(pair[0], pair[1], "20 以内退位", "个位不够减，向十位借 1。这是 20 以内。");
});

export const UNIT_SET = [
  { text: "1 米 = ? 厘米", answer: "100", hint: "1 米是 100 厘米。二年级只练厘米和米。" },
  { text: "100 厘米 = ? 米", answer: "1", hint: "100 厘米就是 1 米。" },
  { text: "2 米 = ? 厘米", answer: "200", hint: "1 米是 100 厘米，2 米就是 200 厘米。" },
];

export const WHY_POOL = {
  gap: { small: [[11, 2], [12, 3], [13, 4], [14, 5]], main: [[12, 5], [13, 6], [14, 8], [15, 7], [11, 4]] },
  fast: { small: [[15, 7], [14, 6], [13, 5], [16, 9]], main: [[46, 19], [63, 27], [54, 18], [72, 35]] },
  slip: { small: [[12, 4], [13, 7], [11, 5], [14, 8]], main: [[53, 28], [64, 37], [85, 49], [91, 46]] },
};

export const WHY_HINT = {
  gap: "还不会：先降一档，做一道更小的退位题",
  fast: "算太快：同难度再来一道，不背原来的答案",
  slip: "看错了：数字换掉，知识点还是退位减法",
};

export function makeRepair(kind, bank, index, gapStreak) {
  const bankKey = bank === "small" ? "small" : "main";
  const pool = (WHY_POOL[kind] || WHY_POOL.slip)[bankKey];
  const pair = pool[(index + gapStreak) % pool.length];
  const kindName = bank === "small" ? "20 以内退位" : "退位减法";
  return subItem(pair[0], pair[1], kindName, WHY_HINT[kind] || WHY_HINT.slip);
}