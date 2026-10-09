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

function lessonItem(prompt, answer, say, choices) {
  return {
    prompt: prompt,
    answer: String(answer),
    say: say,
    choices: choices || null,
  };
}

function copyLessonItem(item) {
  const choices = [];
  if (item.choices) {
    for (let i = 0; i < item.choices.length; i += 1) {
      choices.push({ id: item.choices[i].id, text: item.choices[i].text });
    }
  }
  return {
    prompt: item.prompt,
    answer: item.answer,
    say: item.say,
    choices: item.choices ? choices : null,
  };
}

function pickPool(pool, seed, avoidPrompt) {
  let start = 0;
  if (seed) start = Math.abs(seed) % pool.length;
  let picked = pool[start];
  for (let n = 0; n < pool.length; n += 1) {
    const item = pool[(start + n) % pool.length];
    if (item.prompt !== avoidPrompt) {
      picked = item;
      break;
    }
  }
  return copyLessonItem(picked);
}

const COMPARE_CHOICES = [
  { id: ">", text: "大于" },
  { id: "<", text: "小于" },
  { id: "=", text: "等于" },
];

export const MATH_LESSONS = [
  {
    id: "more-less",
    bands: ["pre"],
    title: "比多少",
    stage: "学前",
    explain: "两堆放在一起。先一个一个数，再看哪一堆更多。",
    chant: "先数数，再比比，多的那边点一点。",
    frames: [
      { title: "先看左边", text: "左边 2 个", dots: ["🍎", "🍎"] },
      { title: "再看右边", text: "右边 3 个", dots: ["🍎", "🍎", "🍎"] },
      { title: "比一比", text: "3 比 2 多，右边更多", dots: ["🍎", "🍎", "🍎"] },
    ],
    model: lessonItem("哪一堆苹果更多？", "right", "右边 3 个，左边 2 个。右边更多。", [
      { id: "left", text: "🍎🍎 左边" },
      { id: "right", text: "🍎🍎🍎 右边" },
    ]),
    drills: [
      lessonItem("哪一堆小狗更多？", "left", "左边 2 只，右边 1 只。左边更多。", [
        { id: "left", text: "🐶🐶 左边" },
        { id: "right", text: "🐶 右边" },
      ]),
      lessonItem("哪一堆星星更多？", "left", "左边 4 颗，右边 2 颗。左边更多。", [
        { id: "left", text: "🌟🌟🌟🌟 左边" },
        { id: "right", text: "🌟🌟 右边" },
      ]),
      lessonItem("哪一堆小车更多？", "right", "左边 1 辆，右边 2 辆。右边更多。", [
        { id: "left", text: "🚗 左边" },
        { id: "right", text: "🚗🚗 右边" },
      ]),
    ],
  },
  {
    id: "split-five",
    bands: ["pre"],
    title: "5 的分与合",
    stage: "学前",
    explain: "5 可以分成两份。两份合起来，还是 5。",
    chant: "分一分，合回来，两份加起还是它。",
    frames: [
      { title: "这是 5 个", text: "先看完整的 5 个", dots: ["🍎", "🍎", "🍎", "🍎", "🍎"] },
      { title: "拿出 2 个", text: "先分成 2 个", dots: ["🍎", "🍎"] },
      { title: "还剩 3 个", text: "2 和 3 合起来还是 5", dots: ["🍎", "🍎", "🍎"] },
    ],
    model: lessonItem("5 可以分成 2 和几？", "3", "2 和 3 合起来是 5。", [
      { id: "1", text: "1" },
      { id: "3", text: "3" },
      { id: "4", text: "4" },
    ]),
    drills: [
      lessonItem("5 可以分成 1 和几？", "4", "1 和 4 合起来是 5。", [
        { id: "2", text: "2" },
        { id: "4", text: "4" },
        { id: "5", text: "5" },
      ]),
      lessonItem("4 可以分成 1 和几？", "3", "1 和 3 合起来是 4。", [
        { id: "2", text: "2" },
        { id: "3", text: "3" },
        { id: "4", text: "4" },
      ]),
      lessonItem("5 可以分成 4 和几？", "1", "4 和 1 合起来是 5。", [
        { id: "1", text: "1" },
        { id: "2", text: "2" },
        { id: "3", text: "3" },
      ]),
    ],
  },
  {
    id: "add-ten",
    bands: ["g12"],
    title: "10 以内加法",
    stage: "一二年级",
    explain: "把两小堆合到一起数。不满 10，就不用进位。",
    chant: "合在一起数一数，不满十，直接数。",
    frames: [
      { title: "先拿 3 个", text: "第一堆是 3 个", dots: ["●", "●", "●"] },
      { title: "再拿 2 个", text: "第二堆是 2 个", dots: ["●", "●"] },
      { title: "合起来", text: "3 和 2 合起来是 5", dots: ["●", "●", "●", "●", "●"] },
    ],
    model: lessonItem("3 + 2 = ?", "5", "3 个再添 2 个，一共 5 个。"),
    drills: [
      lessonItem("4 + 3 = ?", "7", "4 个再添 3 个，一共 7 个。"),
      lessonItem("6 + 2 = ?", "8", "6 个再添 2 个，一共 8 个。"),
      lessonItem("5 + 4 = ?", "9", "5 个再添 4 个，一共 9 个。不满 10。"),
    ],
  },
  {
    id: "borrow-twenty",
    bands: ["g12", "mid"],
    title: "20 以内退位",
    stage: "一二年级",
    explain: "个位不够减，就向十位借 1。借来的 1 个十，等于 10 个一。",
    chant: "个位不够向十借，借一当十再相减。",
    frames: [
      { title: "13 减 5", text: "十位 1，个位 3。个位不够减 5", dots: ["十", "3"] },
      { title: "向十位借", text: "借来的 1 个十，变成 10 个一", dots: ["10"] },
      { title: "再减", text: "13 个一减 5 个一，还剩 8", dots: ["8"] },
    ],
    model: lessonItem("13 − 5 = ?", "8", "个位 3 不够减 5。向十位借 1，13−5=8。"),
    drills: [
      lessonItem("12 − 5 = ?", "7", "个位 2 不够减 5。借 1 以后，12−5=7。"),
      lessonItem("14 − 6 = ?", "8", "个位 4 不够减 6。借 1 以后，14−6=8。"),
      lessonItem("15 − 7 = ?", "8", "个位 5 不够减 7。借 1 以后，15−7=8。"),
    ],
  },
  {
    id: "borrow-tens",
    bands: ["g36", "mid"],
    title: "退位减法",
    stage: "三到六年级",
    explain: "跟 20 以内一样。个位不够，向十位借 1，借走以后十位少 1。这就是口算里的退位减法。",
    chant: "个位不够向十借，十位少一记心里。",
    frames: [
      { title: "32 减 17", text: "个位 2 不够减 7", dots: ["32", "17"] },
      { title: "向十位借", text: "十位 3 变成 2，个位变成 12", dots: ["12"] },
      { title: "再减", text: "12−7=5，十位 2−1=1，得 15", dots: ["15"] },
    ],
    model: lessonItem("32 − 17 = ?", "15", "个位 2 不够减 7。借 1 后，12−7=5，十位 2−1=1，得 15。"),
    drills: [
      lessonItem("41 − 16 = ?", "25", "个位 1 不够减 6。借 1 后，11−6=5，十位 3−1=2，得 25。"),
      lessonItem("53 − 28 = ?", "25", "个位 3 不够减 8。借 1 后，13−8=5，十位 4−2=2，得 25。"),
      lessonItem("70 − 26 = ?", "44", "个位 0 不够减 6。借 1 后，10−6=4，十位 6−2=4，得 44。"),
    ],
  },
  {
    id: "times-row",
    bands: ["g36"],
    title: "三四十二",
    stage: "三到六年级",
    explain: "3 个 4 合在一起是 12。口诀用来记住。今天只复习这一条，不另开一组新题。",
    chant: "三四十二。3 堆，每堆 4 个。",
    frames: [
      { title: "第 1 堆", text: "一堆 4 个", dots: ["●", "●", "●", "●"] },
      { title: "第 2 堆", text: "又是 4 个", dots: ["●", "●", "●", "●"] },
      { title: "第 3 堆", text: "再 4 个。三四十二", dots: ["12"] },
    ],
    model: lessonItem("3 × 4 = ?", "12", "3 个 4 是 12。三四十二。"),
    drills: [
      lessonItem("3 × 2 = ?", "6", "3 个 2 是 6。三二得六。"),
      lessonItem("3 × 5 = ?", "15", "3 个 5 是 15。三五十五。"),
      lessonItem("3 × 3 = ?", "9", "3 个 3 是 9。三三得九。"),
    ],
  },
  {
    id: "around-box",
    bands: ["g36"],
    title: "长方形周长",
    stage: "三到六年级",
    explain: "周长是绕一圈的长度。长方形对边一样长，把两条长和两条宽加起来。",
    chant: "绕一圈，长加宽，再加长，再加宽。",
    frames: [
      { title: "两条长", text: "4 厘米加 4 厘米", dots: ["4", "4"] },
      { title: "两条宽", text: "2 厘米加 2 厘米", dots: ["2", "2"] },
      { title: "绕一圈", text: "4+2+4+2=12", dots: ["12"] },
    ],
    model: lessonItem("长 4 厘米，宽 2 厘米，周长是多少厘米？", "12", "4+2+4+2=12。绕一圈是 12 厘米。"),
    drills: [
      lessonItem("长 5 厘米，宽 3 厘米，周长是多少厘米？", "16", "5+3+5+3=16。绕一圈是 16 厘米。"),
      lessonItem("长 6 厘米，宽 2 厘米，周长是多少厘米？", "16", "6+2+6+2=16。绕一圈是 16 厘米。"),
      lessonItem("长 4 厘米，宽 3 厘米，周长是多少厘米？", "14", "4+3+4+3=14。绕一圈是 14 厘米。"),
    ],
  },
];

export function lessonById(id) {
  for (let i = 0; i < MATH_LESSONS.length; i += 1) {
    if (MATH_LESSONS[i].id === id) return MATH_LESSONS[i];
  }
  return null;
}

export function lessonsForGrade(gradeKey) {
  const list = [];
  for (let i = 0; i < MATH_LESSONS.length; i += 1) {
    if (MATH_LESSONS[i].bands.indexOf(gradeKey) >= 0) list.push(MATH_LESSONS[i]);
  }
  if (!list.length) return [MATH_LESSONS[0]];
  return list;
}

export function redoItem(lesson, seed, avoidPrompt) {
  const pool = [lesson.model].concat(lesson.drills);
  return pickPool(pool, seed, avoidPrompt);
}

export function redoCompare(seed, avoidPrompt) {
  const pool = [
    lessonItem("16 ○ 9", ">", "16 比 9 大。", COMPARE_CHOICES),
    lessonItem("8 ○ 11", "<", "8 比 11 小。", COMPARE_CHOICES),
    lessonItem("14 ○ 14", "=", "两个数一样大。", COMPARE_CHOICES),
    lessonItem("20 ○ 15", ">", "20 比 15 大。", COMPARE_CHOICES),
  ];
  return pickPool(pool, seed, avoidPrompt);
}

export function redoUnit(seed, avoidPrompt) {
  const pool = [];
  for (let i = 0; i < UNIT_SET.length; i += 1) {
    pool.push(lessonItem(UNIT_SET[i].text, UNIT_SET[i].answer, UNIT_SET[i].hint));
  }
  return pickPool(pool, seed, avoidPrompt);
}
